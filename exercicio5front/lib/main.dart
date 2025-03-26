import 'package:exercicio5front/router/router.dart';
import 'package:flutter/material.dart';
import 'package:get/get_navigation/src/root/get_material_app.dart';

void main() => runApp(const MyApp());

/// The main app.
class MyApp extends StatelessWidget {
  /// Constructs a [MyApp]
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
        title: "Exercicio 5",
        initialRoute: "/home",
        getPages: Pages.PAGES,
        locale: const Locale('pt', 'BR'));
  }
}
