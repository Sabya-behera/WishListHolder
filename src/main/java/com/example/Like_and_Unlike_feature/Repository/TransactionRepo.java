package com.example.Like_and_Unlike_feature.Repository;

import com.example.Like_and_Unlike_feature.Model.TransactionCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepo extends JpaRepository<TransactionCounter,Long> {

    @Query(value = "UPDATE transaction_counter SET favourites_counter = favourites_counter+1 where create_date=CURRENT_DATE",nativeQuery = true)
    public Long favouriteCounter(Long id);
    @Query(value = "SELECT favourites_counter from transaction_counter where date(create_date)=CURRENT_DATE",nativeQuery = true)
    Long findByfavouriteCounter(Long id);
    @Query(value = "INSERT into transaction_counter (favourites_counter,create_date) values ('1',CURRENT_DATE)",nativeQuery = true)
    Long insertOne();

    @Query(value = "SELECT mail_counter from transaction_counter where date(create_date)=CURRENT_DATE",nativeQuery = true)
    Long findByMailCounter(Long id);
    @Query(value = "UPDATE transaction_counter SET mail_counter = mail_counter+1 where create_date=CURRENT_DATE",nativeQuery = true)
    public Long MailCounter(Long id);
    @Query(value = "INSERT into transaction_counter (mail_counter,create_date) values ('1',CURRENT_DATE)",nativeQuery = true)
    Long insertTwo();

    @Query(value = "SELECT SUM(favourites_counter) from transaction_counter",nativeQuery = true)
    Long add();

    @Query(value = "SELECT SUM(mail_counter) from transaction_counter",nativeQuery = true)
    Object addmails();
}
