package demo.nacos.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author generator
 * @date 2022-12-19 16:05
 * OpActivity
 */

@Slf4j
@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public Activity detailById(Long id) {
        return activityRepository.findById(id).orElse(null);
    }
}