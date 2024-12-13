package etanah.model;

/**
 * Interface to enable trail auditing of an Entity. Entity has to implement this.
 * @author hishammk
 *
 */
public interface Auditable {
	
	InfoAudit getInfoAudit();

}
