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
<s:form beanclass="etanah.view.stripes.pengambilan.TandatanganDokumenManualV2ActionBean">
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
                                                <!--penggunaList                          -->
                                               <c:forEach items="${actionBean.penggunaPerananList}" var="i" >                           
                                                       <s:option value="${i.pengguna.idPengguna}" >${i.peranan.nama} - ${i.pengguna.nama}</s:option>
                                                   </c:forEach>
                                           </s:select>
                </p>
                <div id="buttontandatangan" align="center">
                <s:button name="simpanTandatangan" class="longbtn" value="Simpan Tandatangan" onclick="doSubmit(this.form,this.name,'page_div')"/>
            </div>
            <br>

            <p>Tempoh Bayaran : 
                <s:select name="bulan" value="${actionBean.senaraiDeposit[0]}">
                    <s:option value="">Sila Pilih..</s:option>
                    <s:option value="1">1</s:option>
                    <s:option value="2">2</s:option>
                    <s:option value="3">3</s:option>
                    <s:option value="4">4</s:option>
                    <s:option value="5">5</s:option>
                    <s:option value="6">6</s:option>
                    <s:option value="7">7</s:option>
                    <s:option value="8">8</s:option>
                    <s:option value="9">9</s:option>
                    <s:option value="10">10</s:option>
                    <s:option value="11">11</s:option>
                    <s:option value="12">12</s:option>
                </s:select>
                Bulan.
            </p>
            <div id="bulan" align="center">
                <s:button name="simpanBulan" class="longbtn" value="Simpan Tempoh" onclick="doSubmit(this.form,this.name,'page_div')"/>
            </div>
        </fieldset>
    </div>

</s:form>