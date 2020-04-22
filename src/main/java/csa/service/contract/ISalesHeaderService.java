package csa.service.contract;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import csa.model.SalesHeader;

@Contract
public interface ISalesHeaderService
{
	List<SalesHeader> listSalesHeaders();
	SalesHeader getSalesHeader(int id);
	SalesHeader createSalesHeader(SalesHeader salesHeader);
	boolean updateSalesHeader(SalesHeader salesHeader);
	boolean deleteSalesHeader(SalesHeader salesHeader);
}
