package etanah.service;

import etanah.model.Dokumen;

public class DmsService {
	
	/**
	 * Add new Dokume with version set to 1.0
	 * @param dokumen
	 * @return the Dokumen which has been updated.
	 */
	public Dokumen addDokkumen(Dokumen dokumen){
		return null;
	}
	
	/**
	 * Update a Dokumen. Algorithm:
	 * 1. Copy the whole Dokumen object and update the Dokumen.idDokumenVersiInduk = Dokumen.idDokumen and
	 *    Dokumen.dokumenVersiPra = Dokumen
	 * 2. In the existing Dokumen:
	 *    Dokumen.noVersi increase
	 *    Dokumen.namaFizikal = newNamaFizikal
	 * @param newNamaFizikal
	 * @return
	 */
	public Dokumen updateDokumen(String newNamaFizikal){
		return null;
	}

}
