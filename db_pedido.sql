-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 11/10/2024 às 04:17
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `db_pedido`
--

DELIMITER $$
--
-- Procedimentos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_Deletar_Cliente` (IN `V_A01_Id` INT)   BEGIN
    -- Excluir itens dos pedidos do cliente
    DELETE FROM item_pedido_04 WHERE A03_Id IN (SELECT A03_Id FROM pedido_03 WHERE A01_Id = V_A01_Id);
    
    -- Excluir pedidos do cliente
    DELETE FROM pedido_03 WHERE A01_Id = V_A01_Id;
    
    -- Excluir o cliente
    DELETE FROM cliente_01 WHERE A01_Id = V_A01_Id;

    COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_Deletar_Pedido` (IN `p_id_pedido` INT)   BEGIN
    DELETE FROM pedido_03
    WHERE A03_Id = p_id_pedido;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_Deletar_Produto` (IN `V_A02_Id` INT)   BEGIN
    -- Excluir itens de pedido associados ao produto
    DELETE FROM item_pedido_04 WHERE A02_Id = V_A02_Id;
    
    -- Excluir o produto
    DELETE FROM produto_02 WHERE A02_Id = V_A02_Id;

    COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_Inserir_Cliente` (IN `V_A01_Nome` VARCHAR(50), IN `V_A01_Endereco` VARCHAR(50), IN `V_A01_Telefone` CHAR(11), IN `V_A01_CPF` CHAR(11), IN `V_A01_Credito` DECIMAL(10,2))   begin insert into Cliente_01 (
	A01_Nome,
    A01_Endereco,
    A01_Telefone,
    A01_CPF,
    A01_Credito
) values (
	V_A01_Nome,
    V_A01_Endereco,
    V_A01_Telefone,
    V_A01_CPF,
    V_A01_Credito
);	
commit;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_Inserir_Pedido` (IN `p_id_cliente` INT, IN `p_data` DATE, IN `p_valor_total` DECIMAL(10,2))   BEGIN
    INSERT INTO pedido_03 (A01_Id, A03_Data, A03_Valor_Total)
    VALUES (p_id_cliente, p_data, p_valor_total);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_Inserir_Produto` (IN `V_A02_Descricao` VARCHAR(50), IN `V_A02_Valor_Unitario` DECIMAL(10,2), IN `V_A02_Estoque` INT)   begin insert into Produto_02 (
	A02_Descricao,
    A02_Valor_Unitario,
    A02_Estoque
    
) values (
	V_A02_Descricao,
    V_A02_Valor_Unitario,
    V_A02_Estoque
);	
commit;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_Listar_Clientes` ()   BEGIN
    -- Seleciona todos os clientes
    SELECT 
        A01_Id,
        A01_Nome, 
        A01_Endereco, 
        A01_Telefone, 
        A01_CPF, 
        A01_Credito
    FROM 
        cliente_01;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_Listar_Pedidos` ()   BEGIN
    SELECT A03_Id, A01_Id, A03_Data, A03_Valor_Total
    FROM pedido_03;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_Listar_Produtos` ()   BEGIN
    -- Seleciona todos os produtos
    SELECT 
        A02_Id, 
        A02_Descricao, 
        A02_Valor_Unitario, 
        A02_Estoque
    FROM 
        produto_02;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_Select_Cliente` (IN `nome` VARCHAR(255))   BEGIN
    SELECT * 
    FROM cliente_01 
    WHERE A01_Nome LIKE CONCAT('%', nome, '%');
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_Select_Produto` (IN `descricao` VARCHAR(255))   BEGIN
    SELECT * 
    FROM produto_02 
    WHERE A02_Descricao LIKE CONCAT('%', descricao, '%');
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_Update_Cliente` (IN `V_A01_Id` INT, IN `V_A01_Nome` VARCHAR(50), IN `V_A01_Endereco` VARCHAR(50), IN `V_A01_Telefone` CHAR(11), IN `V_A01_CPF` CHAR(11), IN `V_A01_Credito` DECIMAL(10,2))   BEGIN
    UPDATE cliente_01
    SET A01_Nome = V_A01_Nome,
        A01_Endereco = V_A01_Endereco,
        A01_Telefone = V_A01_Telefone,
        A01_CPF = V_A01_CPF,
        A01_Credito = V_A01_Credito
    WHERE A01_Id = V_A01_Id;
    COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_Update_Pedido` (IN `p_id_pedido` INT, IN `p_id_cliente` INT, IN `p_data` DATE, IN `p_valor_total` DECIMAL(10,2))   BEGIN
    UPDATE pedido_03
    SET A01_Id = p_id_cliente,
        A03_Data = p_data,
        A03_Valor_Total = p_valor_total
    WHERE A03_Id = p_id_pedido;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_Update_Produto` (IN `V_A02_Id` INT, IN `V_A02_Descricao` VARCHAR(50), IN `V_A02_Valor_Unitario` DECIMAL(10,2), IN `V_A02_Estoque` INT)   BEGIN
    UPDATE produto_02
    SET A02_Descricao = V_A02_Descricao,
        A02_Valor_Unitario = V_A02_Valor_Unitario,
        A02_Estoque = V_A02_Estoque
    WHERE A02_Id = V_A02_Id;
    COMMIT;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `cliente_01`
--

CREATE TABLE `cliente_01` (
  `A01_Id` int(11) NOT NULL,
  `A01_Nome` varchar(50) NOT NULL,
  `A01_Endereco` varchar(50) NOT NULL,
  `A01_Telefone` char(11) NOT NULL,
  `A01_CPF` char(11) NOT NULL,
  `A01_Credito` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `cliente_01`
--

INSERT INTO `cliente_01` (`A01_Id`, `A01_Nome`, `A01_Endereco`, `A01_Telefone`, `A01_CPF`, `A01_Credito`) VALUES
(1, 'Luiz', 'Rua Nome da Rua, Nº da Rua', '11987654321', '98765432100', 1000000.00),
(2, 'Felipe', 'Rua Pinto', '11999998888', '11111111111', 1000000.00),
(3, 'Daniel', 'rua jota', '74999259855', '56734565488', 1000.00),
(4, 'Matheus', 'rua falte', '11999567865', '9876543134', 300.00),
(5, 'gabriel', 'rua dos maniquas', '11999567453', '56545678765', 167.00),
(6, 'platão', 'rua dos pensamentos', '11999567453', '76789897643', 9999.00),
(7, 'luiz', 'teste', '1111111', '1111111', 11111.00);

-- --------------------------------------------------------

--
-- Estrutura para tabela `item_pedido_04`
--

CREATE TABLE `item_pedido_04` (
  `A04_Id` int(11) NOT NULL,
  `A03_Id` int(11) DEFAULT NULL,
  `A02_Id` int(11) DEFAULT NULL,
  `A04_Quantidade` int(11) NOT NULL,
  `A04_Preco_Unitario` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `pedido_03`
--

CREATE TABLE `pedido_03` (
  `A03_Id` int(11) NOT NULL,
  `A01_Id` int(11) NOT NULL,
  `A03_Data` date NOT NULL,
  `A03_Valor_Total` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `pedido_03`
--

INSERT INTO `pedido_03` (`A03_Id`, `A01_Id`, `A03_Data`, `A03_Valor_Total`) VALUES
(1, 1, '2024-09-12', 10.00),
(2, 3, '0008-12-15', 150.00);

-- --------------------------------------------------------

--
-- Estrutura para tabela `produto_02`
--

CREATE TABLE `produto_02` (
  `A02_Id` int(11) NOT NULL,
  `A02_Descricao` varchar(50) NOT NULL,
  `A02_Valor_Unitario` decimal(10,2) NOT NULL,
  `A02_Estoque` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `produto_02`
--

INSERT INTO `produto_02` (`A02_Id`, `A02_Descricao`, `A02_Valor_Unitario`, `A02_Estoque`) VALUES
(1, 'Canetão Azul', 10.00, 10),
(2, 'borracha', 3.00, 700),
(4, 'Caneta', 10.00, 5),
(5, 'apito', 10.00, 500),
(6, 'balões magicos', 15.00, 800),
(7, 'bolacha', 5.00, 1000),
(8, 'casquinha', 2.00, 70);

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `cliente_01`
--
ALTER TABLE `cliente_01`
  ADD PRIMARY KEY (`A01_Id`);

--
-- Índices de tabela `item_pedido_04`
--
ALTER TABLE `item_pedido_04`
  ADD PRIMARY KEY (`A04_Id`),
  ADD KEY `A03_Id` (`A03_Id`),
  ADD KEY `A02_Id` (`A02_Id`);

--
-- Índices de tabela `pedido_03`
--
ALTER TABLE `pedido_03`
  ADD PRIMARY KEY (`A03_Id`),
  ADD KEY `A01_Id` (`A01_Id`);

--
-- Índices de tabela `produto_02`
--
ALTER TABLE `produto_02`
  ADD PRIMARY KEY (`A02_Id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `cliente_01`
--
ALTER TABLE `cliente_01`
  MODIFY `A01_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `item_pedido_04`
--
ALTER TABLE `item_pedido_04`
  MODIFY `A04_Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `pedido_03`
--
ALTER TABLE `pedido_03`
  MODIFY `A03_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `produto_02`
--
ALTER TABLE `produto_02`
  MODIFY `A02_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `item_pedido_04`
--
ALTER TABLE `item_pedido_04`
  ADD CONSTRAINT `item_pedido_04_ibfk_1` FOREIGN KEY (`A03_Id`) REFERENCES `pedido_03` (`A03_Id`) ON DELETE CASCADE,
  ADD CONSTRAINT `item_pedido_04_ibfk_2` FOREIGN KEY (`A02_Id`) REFERENCES `produto_02` (`A02_Id`) ON DELETE CASCADE;

--
-- Restrições para tabelas `pedido_03`
--
ALTER TABLE `pedido_03`
  ADD CONSTRAINT `pedido_03_ibfk_1` FOREIGN KEY (`A01_Id`) REFERENCES `cliente_01` (`A01_Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
