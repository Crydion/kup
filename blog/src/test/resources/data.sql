INSERT INTO USERS(ID, USERNAME, PASSWORD)
VALUES (1, 'Crydion', '$2a$10$5GpavVEHKuTU.Sjmap3A5.kjJufpJlH41kaEyg8X0auaQBmAij4Ru');

INSERT INTO POSTS (ID, PUBLICATION_DATE, AUTHOR, TITLE, CONTENT) 
VALUES (1, NOW(), 'Crydion', 'Lorem ipsum', 'Lorem ipsum dolor sit amet consectetur adipiscing elit duis hac, imperdiet placerat tellus senectus vel dis ullamcorper nibh. Posuere ac eu lacus ultricies suscipit ante tortor nascetur, habitasse ultrices class magnis mauris hendrerit dui integer cum, tempor bibendum in iaculis sed auctor litora. Malesuada feugiat etiam euismod fames, urna consequat.');

INSERT INTO POSTS (ID, PUBLICATION_DATE, AUTHOR, TITLE, CONTENT) 
VALUES (2, NOW(), 'Batar', 'Dragons and other stuff', 'Lorem ipsum dolor sit amet consectetur adipiscing elit duis hac, imperdiet placerat tellus senectus vel dis ullamcorper nibh. Posuere ac eu lacus ultricies suscipit ante tortor nascetur, habitasse ultrices class magnis mauris hendrerit dui integer cum, tempor bibendum in iaculis sed auctor litora. Malesuada feugiat etiam euismod fames, urna consequat.');

INSERT INTO COMMENTS (ID, POST_ID, PUBLICATION_DATE, AUTHOR, CONTENT)
VALUES (1, 1, NOW(), 'Sergy112', 'Im lovin it');
