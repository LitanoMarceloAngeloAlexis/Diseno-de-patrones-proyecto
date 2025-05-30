USE [master]
GO
/****** Object:  Database [GestionCiudadana]    Script Date: 12/05/2025 04:53:09 p. m. ******/
CREATE DATABASE [GestionCiudadana]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'GestionCiudadana', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\GestionCiudadana.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'GestionCiudadana_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\GestionCiudadana_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [GestionCiudadana] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [GestionCiudadana].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [GestionCiudadana] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [GestionCiudadana] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [GestionCiudadana] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [GestionCiudadana] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [GestionCiudadana] SET ARITHABORT OFF 
GO
ALTER DATABASE [GestionCiudadana] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [GestionCiudadana] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [GestionCiudadana] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [GestionCiudadana] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [GestionCiudadana] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [GestionCiudadana] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [GestionCiudadana] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [GestionCiudadana] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [GestionCiudadana] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [GestionCiudadana] SET  ENABLE_BROKER 
GO
ALTER DATABASE [GestionCiudadana] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [GestionCiudadana] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [GestionCiudadana] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [GestionCiudadana] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [GestionCiudadana] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [GestionCiudadana] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [GestionCiudadana] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [GestionCiudadana] SET RECOVERY FULL 
GO
ALTER DATABASE [GestionCiudadana] SET  MULTI_USER 
GO
ALTER DATABASE [GestionCiudadana] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [GestionCiudadana] SET DB_CHAINING OFF 
GO
ALTER DATABASE [GestionCiudadana] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [GestionCiudadana] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [GestionCiudadana] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [GestionCiudadana] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'GestionCiudadana', N'ON'
GO
ALTER DATABASE [GestionCiudadana] SET QUERY_STORE = ON
GO
ALTER DATABASE [GestionCiudadana] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [GestionCiudadana]
GO
/****** Object:  Table [dbo].[AgentePublico]    Script Date: 12/05/2025 04:53:10 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AgentePublico](
	[DNI] [varchar](20) NOT NULL,
	[cargo] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[DNI] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ciudadano]    Script Date: 12/05/2025 04:53:10 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ciudadano](
	[DNI] [varchar](20) NOT NULL,
	[nombre] [varchar](100) NULL,
	[apellido] [varchar](100) NULL,
	[edad] [int] NULL,
	[procedencia] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[DNI] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FuerzaOrden]    Script Date: 12/05/2025 04:53:10 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FuerzaOrden](
	[DNI] [varchar](20) NOT NULL,
	[rango] [varchar](100) NULL,
	[cargo] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[DNI] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SIGDelincuencial]    Script Date: 12/05/2025 04:53:10 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SIGDelincuencial](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Departamento] [varchar](50) NOT NULL,
	[Distrito] [varchar](50) NOT NULL,
	[TasaDelincuencia] [decimal](5, 2) NULL,
	[NivelRiesgo] [varchar](20) NULL,
	[PoblacionAproximada] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tramites]    Script Date: 12/05/2025 04:53:10 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tramites](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[DNI] [varchar](20) NULL,
	[titulo] [varchar](50) NULL,
	[estado] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[AgentePublico] ([DNI], [cargo]) VALUES (N'12345678A', N'Inspector')
INSERT [dbo].[AgentePublico] ([DNI], [cargo]) VALUES (N'23456789B', N'Administrador')
INSERT [dbo].[AgentePublico] ([DNI], [cargo]) VALUES (N'34567890C', N'Director')
INSERT [dbo].[AgentePublico] ([DNI], [cargo]) VALUES (N'45678901D', N'Secretario')
INSERT [dbo].[AgentePublico] ([DNI], [cargo]) VALUES (N'56789012E', N'Supervisor')
GO
INSERT [dbo].[Ciudadano] ([DNI], [nombre], [apellido], [edad], [procedencia]) VALUES (N'01234567J', N'Valeria', N'Cruz', 29, N'Iquitos')
INSERT [dbo].[Ciudadano] ([DNI], [nombre], [apellido], [edad], [procedencia]) VALUES (N'12345678A', N'Juan', N'Pérez', 35, N'Lima')
INSERT [dbo].[Ciudadano] ([DNI], [nombre], [apellido], [edad], [procedencia]) VALUES (N'23456789B', N'Ana', N'García', 40, N'Cusco')
INSERT [dbo].[Ciudadano] ([DNI], [nombre], [apellido], [edad], [procedencia]) VALUES (N'34567890C', N'Luis', N'Martínez', 28, N'Arequipa')
INSERT [dbo].[Ciudadano] ([DNI], [nombre], [apellido], [edad], [procedencia]) VALUES (N'45678901D', N'Carla', N'Ramos', 32, N'Trujillo')
INSERT [dbo].[Ciudadano] ([DNI], [nombre], [apellido], [edad], [procedencia]) VALUES (N'56789012E', N'Mario', N'Salazar', 45, N'Piura')
INSERT [dbo].[Ciudadano] ([DNI], [nombre], [apellido], [edad], [procedencia]) VALUES (N'67890123F', N'Lucía', N'Torres', 30, N'Tacna')
INSERT [dbo].[Ciudadano] ([DNI], [nombre], [apellido], [edad], [procedencia]) VALUES (N'72147777A', N'Antonio', N'Banderas', 35, N'Lima')
INSERT [dbo].[Ciudadano] ([DNI], [nombre], [apellido], [edad], [procedencia]) VALUES (N'78901234G', N'Diego', N'Quispe', 38, N'Puno')
INSERT [dbo].[Ciudadano] ([DNI], [nombre], [apellido], [edad], [procedencia]) VALUES (N'89012345H', N'Sofía', N'Fernández', 27, N'Chiclayo')
INSERT [dbo].[Ciudadano] ([DNI], [nombre], [apellido], [edad], [procedencia]) VALUES (N'90123456I', N'Andrés', N'Lozano', 50, N'Huancayo')
GO
INSERT [dbo].[FuerzaOrden] ([DNI], [rango], [cargo]) VALUES (N'01234567J', N'Coronel', N'Inteligencia')
INSERT [dbo].[FuerzaOrden] ([DNI], [rango], [cargo]) VALUES (N'67890123F', N'Sargento', N'Operaciones')
INSERT [dbo].[FuerzaOrden] ([DNI], [rango], [cargo]) VALUES (N'78901234G', N'Teniente', N'Logística')
INSERT [dbo].[FuerzaOrden] ([DNI], [rango], [cargo]) VALUES (N'89012345H', N'Capitán', N'Seguridad')
INSERT [dbo].[FuerzaOrden] ([DNI], [rango], [cargo]) VALUES (N'90123456I', N'Mayor', N'Comunicaciones')
GO
SET IDENTITY_INSERT [dbo].[SIGDelincuencial] ON 

INSERT [dbo].[SIGDelincuencial] ([ID], [Departamento], [Distrito], [TasaDelincuencia], [NivelRiesgo], [PoblacionAproximada]) VALUES (1, N'Lima', N'San Juan de Lurigancho', CAST(23.75 AS Decimal(5, 2)), N'Alto', 1090000)
INSERT [dbo].[SIGDelincuencial] ([ID], [Departamento], [Distrito], [TasaDelincuencia], [NivelRiesgo], [PoblacionAproximada]) VALUES (2, N'Lima', N'Miraflores', CAST(5.20 AS Decimal(5, 2)), N'Bajo', 99000)
INSERT [dbo].[SIGDelincuencial] ([ID], [Departamento], [Distrito], [TasaDelincuencia], [NivelRiesgo], [PoblacionAproximada]) VALUES (3, N'Lima', N'Comas', CAST(18.40 AS Decimal(5, 2)), N'Medio', 520000)
INSERT [dbo].[SIGDelincuencial] ([ID], [Departamento], [Distrito], [TasaDelincuencia], [NivelRiesgo], [PoblacionAproximada]) VALUES (4, N'Lima', N'Santiago de Surco', CAST(7.80 AS Decimal(5, 2)), N'Bajo', 300000)
INSERT [dbo].[SIGDelincuencial] ([ID], [Departamento], [Distrito], [TasaDelincuencia], [NivelRiesgo], [PoblacionAproximada]) VALUES (5, N'Callao', N'Callao', CAST(25.90 AS Decimal(5, 2)), N'Alto', 475000)
INSERT [dbo].[SIGDelincuencial] ([ID], [Departamento], [Distrito], [TasaDelincuencia], [NivelRiesgo], [PoblacionAproximada]) VALUES (6, N'Lima', N'La Victoria', CAST(22.10 AS Decimal(5, 2)), N'Alto', 189000)
INSERT [dbo].[SIGDelincuencial] ([ID], [Departamento], [Distrito], [TasaDelincuencia], [NivelRiesgo], [PoblacionAproximada]) VALUES (7, N'Lima', N'Los Olivos', CAST(15.60 AS Decimal(5, 2)), N'Medio', 380000)
INSERT [dbo].[SIGDelincuencial] ([ID], [Departamento], [Distrito], [TasaDelincuencia], [NivelRiesgo], [PoblacionAproximada]) VALUES (8, N'Cusco', N'Cusco', CAST(10.45 AS Decimal(5, 2)), N'Medio', 430000)
INSERT [dbo].[SIGDelincuencial] ([ID], [Departamento], [Distrito], [TasaDelincuencia], [NivelRiesgo], [PoblacionAproximada]) VALUES (9, N'La Libertad', N'Trujillo', CAST(19.85 AS Decimal(5, 2)), N'Alto', 800000)
INSERT [dbo].[SIGDelincuencial] ([ID], [Departamento], [Distrito], [TasaDelincuencia], [NivelRiesgo], [PoblacionAproximada]) VALUES (10, N'Lambayeque', N'Chiclayo', CAST(14.30 AS Decimal(5, 2)), N'Medio', 580000)
INSERT [dbo].[SIGDelincuencial] ([ID], [Departamento], [Distrito], [TasaDelincuencia], [NivelRiesgo], [PoblacionAproximada]) VALUES (11, N'Tacna', N'Tacna', CAST(6.90 AS Decimal(5, 2)), N'Bajo', 280000)
INSERT [dbo].[SIGDelincuencial] ([ID], [Departamento], [Distrito], [TasaDelincuencia], [NivelRiesgo], [PoblacionAproximada]) VALUES (12, N'Arequipa', N'Arequipa', CAST(12.75 AS Decimal(5, 2)), N'Medio', 1000000)
INSERT [dbo].[SIGDelincuencial] ([ID], [Departamento], [Distrito], [TasaDelincuencia], [NivelRiesgo], [PoblacionAproximada]) VALUES (13, N'Junín', N'Huancayo', CAST(11.55 AS Decimal(5, 2)), N'Medio', 370000)
INSERT [dbo].[SIGDelincuencial] ([ID], [Departamento], [Distrito], [TasaDelincuencia], [NivelRiesgo], [PoblacionAproximada]) VALUES (14, N'Ucayali', N'Pucallpa', CAST(17.20 AS Decimal(5, 2)), N'Alto', 310000)
INSERT [dbo].[SIGDelincuencial] ([ID], [Departamento], [Distrito], [TasaDelincuencia], [NivelRiesgo], [PoblacionAproximada]) VALUES (15, N'Loreto', N'Iquitos', CAST(20.40 AS Decimal(5, 2)), N'Alto', 450000)
SET IDENTITY_INSERT [dbo].[SIGDelincuencial] OFF
GO
SET IDENTITY_INSERT [dbo].[Tramites] ON 

INSERT [dbo].[Tramites] ([ID], [DNI], [titulo], [estado]) VALUES (1, N'01234567J', N'Pago de Luz', N'Finalizado')
INSERT [dbo].[Tramites] ([ID], [DNI], [titulo], [estado]) VALUES (2, N'12345678A', N'Renovación de DNI', N'En proceso')
INSERT [dbo].[Tramites] ([ID], [DNI], [titulo], [estado]) VALUES (3, N'23456789B', N'Pago de Impuestos Municipales', N'Pendiente')
INSERT [dbo].[Tramites] ([ID], [DNI], [titulo], [estado]) VALUES (4, N'34567890C', N'Solicitud de Subsidio', N'Finalizado')
INSERT [dbo].[Tramites] ([ID], [DNI], [titulo], [estado]) VALUES (5, N'45678901D', N'Pago de Agua', N'En proceso')
INSERT [dbo].[Tramites] ([ID], [DNI], [titulo], [estado]) VALUES (6, N'56789012E', N'Licencia de Conducir', N'Finalizado')
INSERT [dbo].[Tramites] ([ID], [DNI], [titulo], [estado]) VALUES (7, N'67890123F', N'Actualización de Datos', N'Pendiente')
INSERT [dbo].[Tramites] ([ID], [DNI], [titulo], [estado]) VALUES (8, N'72147777A', N'Inscripción Escolar', N'En proceso')
INSERT [dbo].[Tramites] ([ID], [DNI], [titulo], [estado]) VALUES (9, N'78901234G', N'Pago de Predial', N'Finalizado')
INSERT [dbo].[Tramites] ([ID], [DNI], [titulo], [estado]) VALUES (10, N'89012345H', N'Certificado de Residencia', N'Pendiente')
INSERT [dbo].[Tramites] ([ID], [DNI], [titulo], [estado]) VALUES (11, N'90123456I', N'Reclamo por Servicio Público', N'En proceso')
INSERT [dbo].[Tramites] ([ID], [DNI], [titulo], [estado]) VALUES (12, N'12345678A', N'Pago de licencia de conducir', N'Pendiente')
SET IDENTITY_INSERT [dbo].[Tramites] OFF
GO
ALTER TABLE [dbo].[AgentePublico]  WITH CHECK ADD FOREIGN KEY([DNI])
REFERENCES [dbo].[Ciudadano] ([DNI])
GO
ALTER TABLE [dbo].[FuerzaOrden]  WITH CHECK ADD FOREIGN KEY([DNI])
REFERENCES [dbo].[Ciudadano] ([DNI])
GO
ALTER TABLE [dbo].[Tramites]  WITH CHECK ADD FOREIGN KEY([DNI])
REFERENCES [dbo].[Ciudadano] ([DNI])
GO
/****** Object:  StoredProcedure [dbo].[sp_GetAgentesPublicos]    Script Date: 12/05/2025 04:53:10 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_GetAgentesPublicos]
AS
BEGIN
    SELECT 
        c.DNI,
        c.NOMBRE,
        c.APELLIDO,
        c.EDAD,
        c.PROCEDENCIA,
        a.CARGO
    FROM CIUDADANO c
    INNER JOIN AGENTEPUBLICO a ON c.DNI = a.DNI;
END;
GO
/****** Object:  StoredProcedure [dbo].[sp_GetCiudadanos]    Script Date: 12/05/2025 04:53:10 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_GetCiudadanos]
AS
BEGIN
    SELECT *
    FROM CIUDADANO
    
END;
GO
/****** Object:  StoredProcedure [dbo].[sp_GetCiudadanosComunes]    Script Date: 12/05/2025 04:53:10 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_GetCiudadanosComunes]
AS
BEGIN
    SELECT *
    FROM CIUDADANO
    WHERE DNI NOT IN (
        SELECT DNI FROM AGENTEPUBLICO
        UNION
        SELECT DNI FROM FUERZAORDEN
    );
END;
GO
/****** Object:  StoredProcedure [dbo].[sp_GetFuerzaOrden]    Script Date: 12/05/2025 04:53:10 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_GetFuerzaOrden]
AS
BEGIN
    SELECT 
        c.DNI,
        c.NOMBRE,
        c.APELLIDO,
        c.EDAD,
        c.PROCEDENCIA,
        f.RANGO,
        f.CARGO
    FROM CIUDADANO c
    INNER JOIN FUERZAORDEN f ON c.DNI = f.DNI;
END;
GO
/****** Object:  StoredProcedure [dbo].[sp_GetTramites]    Script Date: 12/05/2025 04:53:10 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_GetTramites]
AS
BEGIN
    SELECT * FROM Tramites;
END;
GO
/****** Object:  StoredProcedure [dbo].[sp_SIGDelincuencial]    Script Date: 12/05/2025 04:53:10 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_SIGDelincuencial]
AS
BEGIN
    SELECT * FROM SIGDelincuencial;
END;
GO
USE [master]
GO
ALTER DATABASE [GestionCiudadana] SET  READ_WRITE 
GO
