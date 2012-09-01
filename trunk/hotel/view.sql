CREATE VIEW room_for_checkin AS
  select `r`.`id` AS `room`,`r`.`description` AS `description`,`r`.`room_floor` AS `room_floor`,`r`.`name` AS `name`,`r`.`room_number` AS `room_number`,`ep`.`id_room_type` AS `id_room_type`,`ep`.`price` AS `price`,`ep`.`id_event` AS `id_event`, `bookedTrx`.`status` as `book_status`, `bookedTrx`.`book_time` as `book_time`, `bookedTrx`.`id` as `book_transaction`
  from `hotel`.`room` `r` 
  join `hotel`.`event_price` `ep` on((`ep`.`id_room_type` = `r`.`id_room_type`)) 
  left join `hotel`.`transaction` as bookedTrx on (bookedTrx.id_room = r.id and bookedTrx.status = 0 and date(bookedTrx.book_time) = curdate()  )
  where r.id not in (select id_room from hotel.transaction where status = 1 )
;

