<%-- 
    Document   : tandatangan_dokumen
    Created on : Jun 27, 2011, 10:12:58 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>



<script type="text/javascript">
     $(document).ready( function() {
                
                $('#buttontandatangan').hide();
            });
    function test() {
    
    if($('#namapguna').val() != ""){
       $('#buttontandatangan').show();
    }

}
    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pelupusan.TandatanganDokumenManualV2ActionBean">
    <s:messages/>
    <div align="center"> 
            <fieldset class="aras1">
           <legend>Tandatangan Dokumen Manual</legend>
           <s:hidden name="ptg"/>
            <s:hidden name="ptd"/>
            <s:hidden name="ptgpuu"/>
            <s:hidden name="ptdKanan"/>
             
            <p>Ditandatangan Oleh :
                 <s:select name="ditundatangan" id="namapguna" onchange="test();">
                           <s:option label="Sila Pilih" value="" />                          
                           <c:forEach items="${actionBean.penggunaPerananList}" var="i" >                              
                               <s:option value="${i.idPengguna}" >${i.jawatan} - ${i.nama}</s:option>
                           </c:forEach>
                       </s:select>
            </p>
            <div id="buttontandatangan" align="center">
            <s:button name="simpanTandatangan" class="longbtn" value="Simpan" onclick="doSubmit(this.form,this.name,'page_div')"/>
             </div>
             </fieldset>
                 </div>
             
        <c:if test="${actionBean.flag_perakuan}">
            <p><font color="red">*Sila masukkan tarikh tandatangan terlebih dahulu sebelum menekan butang 'Selesai'. </font></p>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Maklumat Tarikh Tandatangan</legend>
                    <p>
                        <label><font color="red">*</font>Tarikh Tandatangan :</label>
                        <s:text name="tarikh" size="12" id="datepicker" class="datepicker"/>
                    </p>

                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpanTarikh" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </fieldset>
            </div>            
        </c:if>     
             
    
</s:form>