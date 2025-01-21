# Description

Spring Data and JPA pessimistic lock example

# Counter Example

See  
https://medium.com/@beratyesbek/pessimistic-and-optimistic-locking-in-jpa-spring-boot-aa59f2b906fa

This example uses Spring repository

# Post Example

See  
https://vladmihalcea.com/database-job-queue-skip-locked/

This example uses pure EntityManager

# LockModeType.PESSIMISTIC_READ

```sql
SELECT * FROM counter WHERE id=? FOR SHARE
```

Multiple transactions can read the same record simultaneously.  
However, it prevents the acquisition of a write lock on the same record.

# LockModeType.PESSIMISTIC_WRITE

```sql
SELECT * FROM counter WHERE id=? FOR NO KEY UPDATE
```

It provides an exclusive lock, meaning that only one transaction can read or write at a time.

Example 1 for MySQL: 
See  
https://levelup.gitconnected.com/mastering-task-scheduling-essential-tricks-for-senior-spring-boot-developers-934f42e0b425
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Lock(LockMode.PESSIMISTIC_WRITE
  List<User> findTop50ByEmailDeliveryStatus(EmailDeliveryStatusType status);
}
```

```sql
select * from users u
where u.email_delivery_status = 'PENDING'
limit ? 
for update ---> LOCK
```
Example 2 for MySQL:  
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Lock(LockMode.PESSIMISTIC_WRITE
  @QueryHints({
    @QueryHint(name=AvailableHints.HINT_SPEC_LOCK_TIMEOUT, value="-2")
  })
  List<User> findTop50ByEmailDeliveryStatus(EmailDeliveryStatusType status);
}
```
```sql
select * from users u
where u.email_delivery_status = 'PENDING'
limit ? 
for update skip locked---> LOCK
```
# LockModeType.PESSIMISTIC_FORCE_INCREMENT

```sql
SELECT * FROM counter WHERE id=? FOR NO KEY UPDATE NOWAIT
UPDATE ... SET version=? WHERE id=? AND version=?
```

This is pretty similar to PESSIMISTIC_WRITE.     
It additionally increments a version attribute of a versioned entity.
<




