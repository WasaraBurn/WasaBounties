package data.scripts;

import org.apache.log4j.Logger;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.impl.campaign.intel.PersonBountyIntel;
import com.fs.starfarer.api.impl.campaign.intel.PersonBountyManager;

import data.scripts.campaign.WasaPersonBountyManager;

public class WasaBountiesPlugin extends BaseModPlugin {
	
	public static Logger log = Global.getLogger(WasaBountiesPlugin.class);
	

	
    @Override
    public void onNewGameAfterTimePass() {
    	
    Global.getSector().addScript(new WasaPersonBountyManager());

	
    WasaPersonBountyManager.getInstance().advance(6.66f);
    // Not actually sure if this is important, head ache right now.
    // This is a reminder for me to check later
    }
    

}
