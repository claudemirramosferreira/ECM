﻿/*================================================================================*/
/* DDL SCRIPT                                                                     */
/*================================================================================*/
/*  Title    :                                                                    */
/*  FileName : ecm.ecm                                                            */
/*  Platform : MySQL 5.6                                                          */
/*  Version  : Concept                                                            */
/*  Date     : domingo, 30 de agosto de 2015                                      */
/*================================================================================*/

CREATE DATABASE `ecm`;

use `ecm`;

/*================================================================================*/
/* CREATE TABLES                                                                  */
/*================================================================================*/

CREATE TABLE `sys_versao` (
  `id_sys_versao` BIGINT NOT NULL,
  `tx_versao` VARCHAR(40) NOT NULL,
  CONSTRAINT `PK_sys_versao` PRIMARY KEY (`id_sys_versao`)
);

/*================================================================================*/
/* CREATE INDEXES                                                                 */
/*================================================================================*/

CREATE UNIQUE INDEX `IX_sys_versao1` ON `sys_versao` (`tx_versao`);

