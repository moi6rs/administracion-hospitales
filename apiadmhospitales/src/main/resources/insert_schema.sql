
CREATE TABLE `doctores` (
  `id` bigint(20) NOT NULL,
  `actualizado_por` bigint(20) DEFAULT NULL,
  `creado_por` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` date DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `foto` longblob,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `doctor_especialidad` (
  `id` bigint(20) NOT NULL,
  `actualizado_por` bigint(20) DEFAULT NULL,
  `creado_por` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` date DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL,
  `doctor_id` bigint(20) DEFAULT NULL,
  `especialidad_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `especialidades` (
  `id` bigint(20) NOT NULL,
  `actualizado_por` bigint(20) DEFAULT NULL,
  `creado_por` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` date DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL,
  `avatar` longblob,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `hospitales` (
  `id` bigint(20) NOT NULL,
  `actualizado_por` bigint(20) DEFAULT NULL,
  `creado_por` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` date DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `nombre_hospital` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `notas` (
  `id` bigint(20) NOT NULL,
  `actualizado_por` bigint(20) DEFAULT NULL,
  `creado_por` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` date DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha` date NOT NULL,
  `doctor_id` bigint(20) NOT NULL,
  `paciente_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `pacientes` (
  `id` bigint(20) NOT NULL,
  `actualizado_por` bigint(20) DEFAULT NULL,
  `creado_por` bigint(20) DEFAULT NULL,
  `fecha_actualizacion` date DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `foto` longblob,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `name` varchar(60) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


--
-- Indices de la tabla `doctores`
--
ALTER TABLE `doctores`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `doctor_especialidad`
--
ALTER TABLE `doctor_especialidad`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjhs6soy3xucwrcp3nt1a4afxb` (`doctor_id`),
  ADD KEY `FKfb6ffv2s9jhdosk2n2pka7gx6` (`especialidad_id`);

--
-- Indices de la tabla `especialidades`
--
ALTER TABLE `especialidades`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `hospitales`
--
ALTER TABLE `hospitales`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `notas`
--
ALTER TABLE `notas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnvvcy2q3rhy60875i01542b00` (`doctor_id`),
  ADD KEY `FKnulawpqfk3uq0611vcxxd24qb` (`paciente_id`);

--
-- Indices de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_nb4h0p6txrmfc0xbrd1kglp9t` (`name`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Indices de la tabla `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `doctores`
--
ALTER TABLE `doctores`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `doctor_especialidad`
--
ALTER TABLE `doctor_especialidad`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `especialidades`
--
ALTER TABLE `especialidades`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `hospitales`
--
ALTER TABLE `hospitales`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `notas`
--
ALTER TABLE `notas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;
