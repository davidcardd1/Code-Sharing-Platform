package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.model.Code;
import platform.repositories.CodeRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CodeService {
    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }
    public Code findById(String id) {
        Code code = codeRepository.findByIdIs(id);
        if (code != null) {
            if(code.getTimeRestricted()) {
                long passedSeconds = ChronoUnit.SECONDS.between(code.getDateNormal(), LocalDateTime.now());
                code.setTime(code.getTimeOriginal() - passedSeconds);
                if (code.getTime() < 0) {
                    codeRepository.delete(code);
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                } else {
                    codeRepository.save(code);
                }
            }

            if(code.getViewsRestricted()) {
                code.setViews(code.getViews() - 1);
                if(code.getViews() < 0) {
                    codeRepository.delete(code);
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);

                } else {
                    codeRepository.save(code);
                }
            }
            return codeRepository.findByIdIs(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Code save(Code toSave) {
        return codeRepository.save(toSave);
    }

    public List<Code> findLatestCodes() {
        List<Code> list = codeRepository.findAllByViewsRestrictedAndTimeRestricted(false, false);
        Collections.reverse(list);

        return list.stream().limit(10).collect(Collectors.toList());
    }
}
