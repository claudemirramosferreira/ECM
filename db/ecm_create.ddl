/*================================================================================*/
/* DDL SCRIPT                                                                     */
/*================================================================================*/
/*  Title    :                                                                    */
/*  FileName : ecm.ecm                                                            */
/*  Platform : MySQL 5.6                                                          */
/*  Version  : Concept                                                            */
/*  Date     : segunda-feira, 7 de setembro de 2015                               */
/*================================================================================*/
/*================================================================================*/
/* CREATE TABLES                                                                  */
/*================================================================================*/

CREATE TABLE `sys_versao` (
  `id_sys_versao` BIGINT NOT NULL,
  `tx_versao` VARCHAR(40) NOT NULL,
  CONSTRAINT `PK_sys_versao` PRIMARY KEY (`id_sys_versao`)
);

CREATE TABLE `tipo_documento` (
  `id_tipo_documento` BIGINT NOT NULL,
  `tx_tipo_documento` VARCHAR(40) NOT NULL,
  `cs_ativo` BOOL NOT NULL,
  CONSTRAINT `PK_tipo_documento` PRIMARY KEY (`id_tipo_documento`)
);

/*================================================================================*/
/* CREATE INDEXES                                                                 */
/*================================================================================*/

CREATE UNIQUE INDEX `IX_sys_versao1` ON `sys_versao` (`tx_versao`);
