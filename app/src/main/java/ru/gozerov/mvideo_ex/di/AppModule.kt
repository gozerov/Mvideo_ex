package ru.gozerov.mvideo_ex.di

import dagger.Module

@Module(includes = [AbstractBindsModule::class, RemoteModule::class])
class AppModule