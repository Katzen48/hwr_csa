package csa.service;

import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import csa.database.DatabaseAdapter;
import csa.model.Vendor;
import csa.service.contract.IVendorService;

@Service
public class ValueLedgerEntryService implements IVendorService
{
	@Inject
	private DatabaseAdapter dbAdapter;
	
	@Override
	public List<Vendor> listVendors()
	{
		return dbAdapter.listVendors();
	}

	@Override
	public Vendor getVendor(int id)
	{
		return dbAdapter.getVendor(id);
	}

	@Override
	public Vendor createVendor(Vendor vendor)
	{
		return dbAdapter.createVendor(vendor);
	}

	@Override
	public boolean updateVendor(Vendor vendor)
	{
		return dbAdapter.updateVendor(vendor);
	}

	@Override
	public boolean deleteVendor(Vendor vendor)
	{
		return dbAdapter.deleteVendor(vendor);
	}
}
