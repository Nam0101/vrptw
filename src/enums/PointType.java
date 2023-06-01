package enums;

// PointType enum for VRPTW problem. Each point has a type.
// The type is used to determine the constraint of the point.
public enum PointType {
	STARTING_ROUTE,
	TERMINATING_ROUTE,
	CLIENT
}
