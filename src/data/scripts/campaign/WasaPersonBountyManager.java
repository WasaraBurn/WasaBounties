package data.scripts.campaign;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.impl.campaign.intel.BaseEventManager;
import com.fs.starfarer.api.impl.campaign.intel.PersonBountyIntel;
import com.fs.starfarer.api.impl.campaign.intel.PersonBountyManager;

import org.apache.log4j.Logger;

public class WasaPersonBountyManager extends BaseEventManager {

	public static final String KEY = "$core_WasapersonBountyManager";
	public static Logger log = Global.getLogger(WasaPersonBountyManager.class);
	
	public static WasaPersonBountyManager getInstance() {
		Object test = Global.getSector().getMemoryWithoutUpdate().get(KEY);
		return (WasaPersonBountyManager) test; 
	}
	
	public WasaPersonBountyManager() {
		super();
		Global.getSector().getMemoryWithoutUpdate().set(KEY, this);
	}
	
	@Override
	protected int getMinConcurrent() {
		return Global.getSettings().getInt("minPersonBounties");
	}
	@Override
	protected int getMaxConcurrent() {
		return Global.getSettings().getInt("maxPersonBounties");
	}
	
	@Override
	protected float getIntervalRateMult() {
//		if (true) {
//			currMax = 200;
//			return 1000f;
//		}
		return super.getIntervalRateMult();
	}

	@Override
	protected EveryFrameScript createEvent() {
		if ((float) Math.random() < 0.01f) return null;
		
		WasaPersonBountyIntel intel = new WasaPersonBountyIntel();
		if (intel.isDone()) intel = null;

		return intel;
	}
// Below is mostly borrowed from Vayra Sector. it neuters the vanilla bounty system
	@Override
	   public void advance(float amount) {
			
	        super.advance(amount);
			
	        if (amount == 6.66f) {
	            for (EveryFrameScript s : PersonBountyManager.getInstance().getActive()) {
	                ((PersonBountyIntel) s).endImmediately();
	                Global.getSector().removeScript(s);
	                log.info(String.format("Wasa: Why am I getitng type cast errors!?!?!"));
	            }
	        }
			
			if (Global.getSector().hasScript(PersonBountyManager.class)) {
	        	Global.getSector().removeScript(PersonBountyManager.getInstance());
	        	log.info(String.format("Wasa: Failed to removed unwanted trash manager"));
			}
	    }
		
	
}
