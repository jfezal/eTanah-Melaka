<%-- 
    Document   : rayuan_lanjut_tempoh_bayaran
    Created on : Sep 5, 2010, 2:56:07 PM
    Author     : sitifariza.hanim
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
//    function checking(){
//alert(document.lanjut.urusan.value);
//        function checking() {
//            if(document.lanjut.urusan.value == 'RAYL'){
//                if($('#tempohBulan').val() > 6){
//                    alert("Sila masukkan tempoh bulan di antara 1 hingga 6 bulan") ;
//                    return false ;
//                }
//            }
//            if(document.lanjut.urusan.value == 'RLPTG'){
//                if($('#tempohBulan').val() > 12){
//                    alert("Sila masukkan tempoh bulan di antara 1 hingga 12 bulan") ;
//                    return false
//                }
//            }
//                
//            return true ;
//                
//                
//        }
//    }
    
</script>

<s:form  beanclass="etanah.view.stripes.pelupusan.PermohonanRAYTActionBean" name="lanjut">
<s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Rayuan Lanjut Tempoh Bayaran</legend>
            <s:hidden name="${actionBean.permohonan.kodUrusan.kod}" id="urusan" value="${actionBean.permohonan.kodUrusan.kod}"/>
            <c:if test="${edit}">
              <p>
                <label>Tempoh Asal Bayaran :</label>
                3 Bulan
            </p>
            
            <p>
                <label>Tempoh Dipohon :</label>
                <s:text name="permohonan.tempohBulan" id="tempohBulan"/>&nbsp; Bulan
            </p>
             <p>
                <label>Butir-butir Rayuan</label>
                <s:textarea name="permohonan.sebab" cols="100" rows="10" />
            </p>
                <p>
                    <label>&nbsp;</label>

                    <s:button name="simpanLanjut" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
                </p>
            </c:if>
             <c:if test="${!edit}">
              <p>
                <label>Tempoh Asal Bayaran :</label>
                3 Bulan
            </p>
            
            <p>
                <label>Tempoh Dipohon :</label>
                ${actionBean.permohonan.tempohBulan}&nbsp; Bulan
            </p>
             <p>
                <label>Butir-butir Rayuan :</label>
                <s:textarea readonly="true" value="${actionBean.permohonan.sebab}" name="readOnlyBRayuan" cols="80" rows="5"/>
            </p>
            </c:if>    
        </fieldset >
    </div>
</s:form>

