-- Recurring weekly schedule
CREATE TABLE ProviderRecurringSchedule (
                                           id SERIAL PRIMARY KEY,
                                           provider_id BIGINT NOT NULL,
                                           day_of_week VARCHAR(10) NOT NULL,  -- E.g., 'MONDAY'
                                           start_time TIME NOT NULL,
                                           end_time TIME NOT NULL
);

-- Specific date schedule
CREATE TABLE ProviderSpecificSchedule (
                                          id SERIAL PRIMARY KEY,
                                          provider_id BIGINT NOT NULL,
                                          date DATE NOT NULL,
                                          start_time TIME NOT NULL,
                                          end_time TIME NOT NULL
);

-- Booked slots
CREATE TABLE BookedSlot (
                            id SERIAL PRIMARY KEY,
                            provider_id BIGINT NOT NULL,
                            start_time TIMESTAMP NOT NULL,
                            end_time TIMESTAMP NOT NULL
);

INSERT INTO ProviderRecurringSchedule (provider_id, day_of_week, start_time, end_time)
VALUES
    (1, 'MONDAY', '09:00:00', '17:00:00'),
    (1, 'TUESDAY', '09:00:00', '17:00:00'),
    (1, 'WEDNESDAY', '09:00:00', '17:00:00'),
    (1, 'THURSDAY', '09:00:00', '17:00:00'),
    (1, 'FRIDAY', '09:00:00', '17:00:00');

INSERT INTO ProviderSpecificSchedule (provider_id, date, start_time, end_time)
VALUES
    (1, '2023-08-14', '10:00:00', '15:00:00'),  -- Override Monday's schedule
    (1, '2023-08-18', '11:00:00', '16:00:00');  -- Override Friday's schedule


INSERT INTO BookedSlot (provider_id, start_time, end_time)
VALUES
    (1, '2023-08-14 10:30:00', '2023-08-14 11:00:00'),
    (1, '2023-08-14 12:00:00', '2023-08-14 12:30:00'),
    (1, '2023-08-18 11:30:00', '2023-08-18 12:00:00');
