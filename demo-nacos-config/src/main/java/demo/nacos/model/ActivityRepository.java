package demo.nacos.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author generator
 * @date 2022-12-19 16:05
 * OpActivity
 */

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

}