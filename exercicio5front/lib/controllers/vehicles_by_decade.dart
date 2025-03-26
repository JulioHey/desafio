import 'package:exercicio5front/model/vehicles.dart';
import 'package:exercicio5front/provider/vehicle_provider.dart';
import 'package:get/get.dart';

class VehiclesByDecadeController extends GetxController {
  VehiclesByDecadeController();
  VehicleProvider vehicleProvider = VehicleProvider();

  final RxList<Vehicle> _vehiclesByDecade = <Vehicle>[].obs;
  final Rx<int> _decade = 2020.obs;

  List<Vehicle> get vehiclesByDecade => _vehiclesByDecade.toList();

  int get decade => _decade.value;

  @override
  void onReady() {
    super.onReady();
    getVehiclesByDecade();
  }

  void changeDecade(int newDecade) {
    _decade.value = newDecade;
    getVehiclesByDecade();
  }

  Future<void> getVehiclesByDecade() async {
    final vehicles = await vehicleProvider.getVehiclesByDecade(_decade.value);

    _vehiclesByDecade.assignAll(vehicles);
  }
}
