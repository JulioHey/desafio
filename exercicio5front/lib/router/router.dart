import 'package:exercicio5front/bindings/home_binding.dart';
import 'package:exercicio5front/bindings/vehicle.dart';
import 'package:exercicio5front/pages/create_vehicle.dart';
import 'package:exercicio5front/pages/edit_vehicle.dart';
import 'package:exercicio5front/pages/home.dart';
import 'package:exercicio5front/pages/vehicles_by_brand.dart';
import 'package:exercicio5front/pages/vehicles_by_decade.dart';
import 'package:get/get.dart';

class Pages {
  static final PAGES = [
    GetPage(
      name: "/home",
      page: () => HomePage(),
      binding: HomeBinding(),
      maintainState: false,
    ),
    GetPage(
        name: "/edit/:id",
        page: () => EditVehiclePage(),
        binding: VehiclesBinding()),
    GetPage(
        name: "/create",
        page: () => CreateVehiclePage(),
        binding: VehiclesBinding()),
    GetPage(
        name: "/by-brand",
        page: () => VehiclesByBrandPage(),
        binding: VehiclesBinding()),
    GetPage(
        name: "/by-decade",
        page: () => VehiclesByDecade(),
        binding: VehiclesBinding()),
  ];
}
