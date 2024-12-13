package etanah.sequence;

import etanah.model.KodCawangan;

/**
 * Format ID Permohonan: <2-KodNegeri><2-KodDaerah><3-KodModul><4-Tahun><6-NoSiri> total 16 digits
 * TODO create scheduler to reset for the year
 * Every kod Jabatan would need a sequence with format seq_mohon_kodJabatan
 * @author hishammk
 *
 */

public class GeneratorIdKumpulanPermohonan extends SequenceGenerator{	

	@Override
	public String generate(String kodNegeri, KodCawangan caw, Object obj) {
		String id = String.valueOf(getSerialNo("seq_mohon_kump"));
                if (IS_DEBUG) LOG.debug(id);
                return id;
	}

}
