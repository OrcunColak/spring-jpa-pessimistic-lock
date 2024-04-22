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

# LockModeType.PESSIMISTIC_FORCE_INCREMENT

```sql
SELECT * FROM counter WHERE id=? FOR NO KEY UPDATE NOWAIT
UPDATE ... SET version=? WHERE id=? AND version=?
```

This is pretty similar to PESSIMISTIC_WRITE.     
It additionally increments a version attribute of a versioned entity.
<




