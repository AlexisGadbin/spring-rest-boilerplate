-- TODO: SUPPRIMER CE FICHIER AVANT LIVRAISON
INSERT INTO users (created_date, last_modified_date, email, name, password, role) 
VALUES 
(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin@admin.com', 'Admin', '$2y$10$xqg98AvQRsQhJYTFc1yBduYg/3NQkRr4sucK4NmPc5qRvXbNltdpi', 'ADMIN');
