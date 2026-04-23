package com.cascarita.app;

import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedEntryPoint;

@OriginatingElement(
    topLevelClass = CascaritaApp.class
)
@GeneratedEntryPoint
@InstallIn(SingletonComponent.class)
public interface CascaritaApp_GeneratedInjector {
  void injectCascaritaApp(CascaritaApp cascaritaApp);
}
