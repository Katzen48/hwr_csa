package csa.service.contract;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import csa.model.Vendor;

@Contract
public interface IVendorService
{
	List<Vendor> listVendors();
	Vendor getVendor(int id);
	Vendor createVendor(Vendor vendor);
	boolean updateVendor(Vendor vendor);
	boolean deleteVendor(Vendor vendor);
}
