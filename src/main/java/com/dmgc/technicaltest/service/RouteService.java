package com.dmgc.technicaltest.service;

import com.dmgc.technicaltest.exception.CustomExeption;
import com.dmgc.technicaltest.model.Route;
import com.dmgc.technicaltest.model.SubRoute;
import com.dmgc.technicaltest.model.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class RouteService {


    private List<String> townList;
    private List<Route> routes;

    @Autowired
    UtilService utilService;


    /**
     * Get distance from a town list
     *
     * @param towns List of towns, it must be sort by the track route
     * @return integer value of the distance
     * @throws CustomExeption when the list dont contain at least two towns, and when no such a route
     */
    public int getDistanceFromTownList(List<Town> towns) throws CustomExeption {
        if (towns.size() < 2) throw new CustomExeption("ENTER AT LEAST TWO TOWNS");
        int distance = 0;
        Town previusTown = null;
        Town currentTown;
        for (Iterator<Town> it = towns.iterator(); it.hasNext(); ) {
            if (previusTown == null) {
                previusTown = it.next();
            }
            currentTown = it.next();
            distance += getDistance(previusTown, currentTown);
            if (distance == 0) throw new CustomExeption("NO SUCH ROUTE");
            previusTown = currentTown;
        }
        return distance;
    }

    /**
     * This method calculate the SubRoutes from max distance
     * @param origin origin town
     * @param destiny destiny town
     * @param maxDistance set 0 if no need
     * @return list of subroutes match with max distance
     */
    public int getCountRoutesByMaxDistance(Town origin, Town destiny, int maxDistance) {
        return getSubRoutesByMaxStopsOrMaxDistance(origin, destiny, 0, maxDistance).size();
    }

    /**
     * This method calculate the SubRoutes from max stops
     * @param origin origin town
     * @param destiny destiny town
     * @param maxStops set 0 if no need
     * @return list of subroutes match with exact stops
     */
    public int getCountRoutesByMaxStops(Town origin, Town destiny, int maxStops) {
        return getSubRoutesByMaxStopsOrMaxDistance(origin, destiny, maxStops, 0).size();
    }

    /**
     * This method calculate the SubRoutes from exact stops
     * @param origin origin town
     * @param destiny destiny town
     * @param exactStops set 0 if no need
     * @return list of subroutes match with exact stops
     */
    public int getCountRoutesByExactStops(Town origin, Town destiny, int exactStops) {
        return (int) getSubRoutesByMaxStopsOrMaxDistance(origin, destiny, exactStops, 0).stream().filter(st -> st.getLastStopsCount() == exactStops).count();
    }

    /**
     * This method calculate the SubRoutes from max stops number or max distance
     * @param origin origin town
     * @param destiny destiny town
     * @param maxStops set 0 if no need
     * @param maxDistance set 0 if no need
     * @return list of subroutes match with maxStops or max distance
     */
    private List<SubRoute> getSubRoutesByMaxStopsOrMaxDistance(Town origin, Town destiny, int maxStops, int maxDistance) {
        int indexOrigin = getIndexFromTown(origin);
        int indexDestiny = getIndexFromTown(destiny);
        List<SubRoute> listSubRouteToReturn = new ArrayList<>();
        PriorityQueue<SubRoute> toProcess = new PriorityQueue<SubRoute>();
        SubRoute subRouteOrigen = new SubRoute(indexOrigin, 0.0);
        subRouteOrigen.addTown(origin);
        toProcess.add(subRouteOrigen);
        while (!toProcess.isEmpty()) {
            SubRoute subRoute = toProcess.remove();
            int v = subRoute.getDest();
            List<Route> adj = getRoutesFromTown(getTownFromIndex(v));
            for (Route a : adj) {
                Town destinyTown = a.getDestiny();
                int dest = getIndexFromTown(destinyTown);
                int p = (int) (a.getDistance() + subRoute.getPeso());
                if ((maxStops > 0 && subRoute.getLastStopsCount() < maxStops)
                        || (maxDistance > 0 && p < maxDistance)) {
                    SubRoute subRouteToProcess = new SubRoute(dest, p, subRoute.getLastStopsCount() + 1, subRoute.getTowns());
                    subRouteToProcess.addTown(destinyTown);
                    toProcess.add(subRouteToProcess);
                    if (indexDestiny == dest )
                        listSubRouteToReturn.add(subRouteToProcess);
                }
            }
        }
        return listSubRouteToReturn;
    }


    /**
     * Calculate the short distance between origin and destiny based in Dijkstra method
     * This method is a variant of Dijkstra to support same town as origin and destiny
     * @param origen
     * @param destino
     * @return
     */
    public double getShortDistanceRoute(Town origen, Town destino) {
        int indexOrigen = getIndexFromTown(origen);
        int indexDestiny = getIndexFromTown(destino);

        boolean tempProcesado = !(indexOrigen == indexDestiny); //Es usando para cuando el Origen y destino son la misma ciudad

        PriorityQueue<SubRoute> procesar = new PriorityQueue<SubRoute>();
        int nodes = townList.size();
        int[] anterior = new int[nodes];
        double[] distance = new double[nodes];
        boolean[] procesado = new boolean[nodes];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Double.POSITIVE_INFINITY;
        }
        if (tempProcesado) {
            distance[indexOrigen] = 0.0;
        }
        procesar.add(new SubRoute(indexOrigen, 0.0));
        anterior[indexOrigen] = -1;
        while (!procesar.isEmpty()) {
            int v = procesar.remove().getDest();
            if (!procesado[v]) {
                procesado[v] = tempProcesado;
                List<Route> adj = getRoutesFromTown(getTownFromIndex(v));
                for (Route a : adj) {
                    int dest = getIndexFromTown(a.getDestiny());
                    int p = a.getDistance();
                    if (!tempProcesado) {
                        anterior[dest] = v;
                        distance[dest] = p;
                        procesar.add
                                (new SubRoute(dest, distance[dest]));
                    }
                    if (distance[dest] > distance[v] + p) {
                        anterior[dest] = v;
                        distance[dest] = distance[v] + p;
                        procesar.add
                                (new SubRoute(dest, distance[dest]));
                    }
                }
            }
            tempProcesado = true;
        }
        if (Double.isInfinite(distance[indexDestiny])) {
            throw new CustomExeption("No existe");
        } else {
            return distance[indexDestiny];
        }
    }

    private List<Route> getRoutesFromTown(Town origin) {
        List<Route> routes = this.routes.stream().filter(route -> route.getOrigin().getName().equals(origin.getName())).collect(Collectors.toList());
        return routes;
    }

    /***
     * Get the distance between origin and destiny
     * @param origin origin town
     * @param destiny destiny town
     * @return int of distance between origin and destiny
     * @throws CustomExeption Exception when NO SUCH ROUTE
     */
    private int getDistance(Town origin, Town destiny) throws CustomExeption {
        int distance = 0;
        Optional<Route> route = routes.stream().filter(r -> r.getOrigin().getName().equals(origin.getName()) && r.getDestiny().getName().equals(destiny.getName())).findFirst();
        if(route.isPresent()){
            distance = route.get().getDistance();
        }
        if (distance == 0) throw new CustomExeption("NO SUCH ROUTE");
        return distance;
    }

    /**
     * get index from sorted town array
     * @param town town to search into array town
     * @return match Town into sorted town array
     */
    private int getIndexFromTown(Town town) {
        return townList.indexOf(town.getName());
    }

    /**
     * get town from sorted town array
     * @param indexTown index int
     * @return match Town into sorted town array
     * @throws CustomExeption Exception if index error
     */
    private Town getTownFromIndex(int indexTown) throws CustomExeption {
        if (indexTown > townList.size()) throw new CustomExeption("INDEX ERROR");
        return new Town(townList.get(indexTown));
    }

    /**
     * Add and validate restriction data to routes list
     * @param route route to add and validate
     * @throws CustomExeption
     */
    public void addRoute(Route route) throws CustomExeption{
        if(route.getDestiny().getName().equals(route.getOrigin().getName())){
            throw new CustomExeption("THE ORIGIN AND DESTINATION CANNOT BE THE SAME TOWN");
        }
        Optional<Route> optionalRoute = routes.stream().filter(r -> r.getOrigin().getName().equals(route.getOrigin().getName()) && r.getDestiny().getName().equals(route.getDestiny().getName())).findFirst();
        if(optionalRoute.isPresent()){
            throw new CustomExeption("ROUTE ALREADY EXISTS");
        }
        this.routes.add(route);
    }

    /**
     * Fill and init route data, this method must be called before use question classes
     * @param routeList List of routes
     */
    public void initData(List<Route> routeList) {
        routes = new ArrayList<>();
        routeList.forEach(this::addRoute);

        TreeSet<String> towns = new TreeSet<String>(); //Elimino las ciudades repetidas en un Set y ordeno alfabéticamente
        for (Route route : routes) {
            towns.add(route.getOrigin().getName());
            towns.add(route.getDestiny().getName());
        }
        townList = new ArrayList<>(towns);

    }
}
