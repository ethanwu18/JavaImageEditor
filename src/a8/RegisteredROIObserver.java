package a8;

public interface RegisteredROIObserver extends ROIObserver {

	Region getRegion();
	ROIObserver getObserver();
}
