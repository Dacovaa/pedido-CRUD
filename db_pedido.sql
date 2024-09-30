-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 19-Set-2024 às 21:23
-- Versão do servidor: 8.0.32
-- versão do PHP: 8.2.0

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

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_Inserir_Item` (IN `V_A04_Id` INT, IN `V_A03_Id` INT, IN `V_A02_Id` INT, IN `V_A04_Quantidade` INT, IN `V_A04_Valor_Item` DECIMAL(10,2))   begin insert into Item_Pedido_04 (
    A04_Id,
    A03_Id,
    A02_Id,
    A04_Quantidade,
    A04_Valor_Item
) values (
    V_A04_Id,
    V_A03_Id,
    V_A02_Id,
    V_A04_Quantidade,
    V_A04_Valor_Item
);	
commit;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_Inserir_Pedido` (IN `V_A01_Id` INT, IN `V_A03_Data` DATE, IN `V_A03_Valor_Total` DECIMAL(10,2))   begin insert into Pedido_03(
	A01_Id,
    A03_Data,
    A03_Valor_Total
) 
values (
	V_A01_Id,
    V_A03_Data,
    V_A03_Valor_Total
);	
commit;
end$$

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

DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente_01`
--

CREATE TABLE `cliente_01` (
  `A01_Id` int NOT NULL,
  `A01_Nome` varchar(50) NOT NULL,
  `A01_Endereco` varchar(50) NOT NULL,
  `A01_Telefone` char(11) NOT NULL,
  `A01_CPF` char(11) NOT NULL,
  `A01_Credito` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `cliente_01`
--

INSERT INTO `cliente_01` (`A01_Id`, `A01_Nome`, `A01_Endereco`, `A01_Telefone`, `A01_CPF`, `A01_Credito`) VALUES
(1, 'Luiz', 'Rua Nome da Rua, Nº da Rua', '11987654321', '98765432100', '1000000.00'),
(2, 'Felipe', 'Rua Pinto', '11999998888', '11111111111', '1000000.00'),
(3, 'Daniel', 'rua jota', '74999259855', '56734565488', '1000.00'),
(4, 'mateus gay', 'gaylandia', '11999242424', '45634598766', '24.00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `item_pedido_04`
--

CREATE TABLE `item_pedido_04` (
  `A04_Id` int NOT NULL,
  `A03_Id` int NOT NULL,
  `A02_Id` int NOT NULL,
  `A04_Quantidade` int NOT NULL,
  `A04_Valor_Item` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido_03`
--

CREATE TABLE `pedido_03` (
  `A03_Id` int NOT NULL,
  `A01_Id` int NOT NULL,
  `A03_Data` date NOT NULL,
  `A03_Valor_Total` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `pedido_03`
--

INSERT INTO `pedido_03` (`A03_Id`, `A01_Id`, `A03_Data`, `A03_Valor_Total`) VALUES
(1, 1, '2024-09-12', '10.00'),
(2, 3, '0008-12-15', '150.00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto_02`
--

CREATE TABLE `produto_02` (
  `A02_Id` int NOT NULL,
  `A02_Descricao` varchar(50) NOT NULL,
  `A02_Valor_Unitario` decimal(10,2) NOT NULL,
  `A02_Estoque` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `produto_02`
--

INSERT INTO `produto_02` (`A02_Id`, `A02_Descricao`, `A02_Valor_Unitario`, `A02_Estoque`) VALUES
(1, 'Canetão Azul', '10.00', 10),
(2, 'Exemplo', '1.00', 1),
(3, '', '10.00', 5),
(4, 'Caneta', '10.00', 5);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `cliente_01`
--
ALTER TABLE `cliente_01`
  ADD PRIMARY KEY (`A01_Id`);

--
-- Índices para tabela `item_pedido_04`
--
ALTER TABLE `item_pedido_04`
  ADD PRIMARY KEY (`A03_Id`,`A02_Id`,`A04_Id`),
  ADD KEY `A02_Id` (`A02_Id`);

--
-- Índices para tabela `pedido_03`
--
ALTER TABLE `pedido_03`
  ADD PRIMARY KEY (`A03_Id`),
  ADD KEY `A01_Id` (`A01_Id`);

--
-- Índices para tabela `produto_02`
--
ALTER TABLE `produto_02`
  ADD PRIMARY KEY (`A02_Id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `cliente_01`
--
ALTER TABLE `cliente_01`
  MODIFY `A01_Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `pedido_03`
--
ALTER TABLE `pedido_03`
  MODIFY `A03_Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `produto_02`
--
ALTER TABLE `produto_02`
  MODIFY `A02_Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `item_pedido_04`
--
ALTER TABLE `item_pedido_04`
  ADD CONSTRAINT `item_pedido_04_ibfk_1` FOREIGN KEY (`A02_Id`) REFERENCES `produto_02` (`A02_Id`),
  ADD CONSTRAINT `item_pedido_04_ibfk_2` FOREIGN KEY (`A03_Id`) REFERENCES `pedido_03` (`A03_Id`);

--
-- Limitadores para a tabela `pedido_03`
--
ALTER TABLE `pedido_03`
  ADD CONSTRAINT `pedido_03_ibfk_1` FOREIGN KEY (`A01_Id`) REFERENCES `cliente_01` (`A01_Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
