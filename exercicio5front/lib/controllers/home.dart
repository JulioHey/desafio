import 'package:exercicio5front/controllers/base_controller.dart';
import 'package:exercicio5front/model/vehicles.dart';
import 'package:exercicio5front/provider/vehicle_provider.dart';
import 'package:get/get.dart';
import 'package:get/get_rx/get_rx.dart';

class HomeController extends BaseController {
  VehicleProvider vehicleProvider = VehicleProvider();
  Rx<bool> poped = true.obs;

  final RxList<Vehicle> _vehiclesLastWeek = <Vehicle>[].obs;

  List<Vehicle> get vehiclesLastWeek => _vehiclesLastWeek.toList();

  RxInt notSoldVehicles = 0.obs;

  @override
  void onReady() {
    getVehiclesLastWeek();
    getNotSoldVehicles();
    super.onReady();
  }

  Future<void> getNotSoldVehicles() async {
    notSoldVehicles.value = await vehicleProvider.getNotSoldVehicles();
  }

  Future<void> getVehiclesLastWeek() async {
    final vehicles = await vehicleProvider.getVehiclesLastWeek();

    _vehiclesLastWeek.assignAll(vehicles);
    poped.value = false;
  }
}
