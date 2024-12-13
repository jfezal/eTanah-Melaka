<%-- 
    Document   : tandatangan_dokumen
    Created on : Jun 27, 2011, 10:12:58 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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
<s:form beanclass="etanah.view.stripes.pelupusan.TandatanganDokumenManualActionBean">
    <s:messages/>
    <div align="center"> 
            <fieldset class="aras1">
           <legend>Tandatangan Dokumen Manual</legend>
           <s:hidden name="ptg"/>
            <s:hidden name="ptd"/>
            <p>Ditandatangan Oleh :
                 <s:select name="ditundatangan" id="namapguna" onchange="test();">
                           <s:option label="Sila Pilih" value="" />                          
                           <c:forEach items="${actionBean.penggunaList}" var="i" >                              
                               <s:option value="${i.idPengguna}" >${i.jawatan} - ${i.nama}</s:option>
                           </c:forEach>
                       </s:select>
            </p>
            <div id="buttontandatangan" align="center">
            <s:button name="simpanTandatangan" class="longbtn" value="Simpan" onclick="doSubmit(this.form,this.name,'page_div')"/>
             </div>
             </fieldset>
                 </div>
    
</s:form>