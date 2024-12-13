<%-- 
    Document   : laporan_operasi
    Created on : Jan 19, 2010, 6:18:05 PM
    Author     : farah.shafilla
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    
    function test(f) {
        $(f).clearForm();
    }
    function validateForm(){

        if($('#tkhOp').val()=="")
        {
            alert("Sila Masukkan Tarikh Operasi");
            return false;
        }
        return true;
    }
</script>
<s:form beanclass="etanah.view.penguatkuasaan.OperasiActionBean" name="form1">
    <s:messages />
   <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Laporan Operasi
            </legend>
            <c:if test="${edit}">
            <div class="content">
                
                    <p>
                        <label>Tarikh Operasi :</label>
                        <s:text name="tarikhOperasi" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tkhOp"/>
                        <font color="red" size="1">cth : hh / bb / tttt</font>&nbsp;
                    </p>
                     <p>
                        <label>Lokasi Operasi :</label>
                         <s:textarea name="lokasi" rows="5" cols="50" />&nbsp;
                    </p>
                    <p>
                        <label>Laporan Operasi :</label>
                         <s:textarea name="operasiPenguatkuasaan.catatan" rows="5" cols="50" />&nbsp;
                    </p>
                
            </div>
        </fieldset>
                     <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                     <%--<s:hidden name="idOperasi" value="${actionBean.operasiPenguatkuasaan.idOperasi}"/>--%>
                     <p align="right">
            <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
            <s:button class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
        </p>
            </c:if>
        <c:if test="${view}">
        <div class="content">

                    <p>
                        <label>Tarikh Operasi :</label>
                        <fmt:formatDate value="${actionBean.operasiPenguatkuasaan.tarikhOperasi}" pattern="dd/MM/yyyy" />&nbsp;
                    </p>
                    <p>
                        <label>Lokasi Operasi :</label>
                       ${actionBean.operasiPenguatkuasaan.lokasi}&nbsp;
                    </p>
                    <p>
                    <table>
                        <tr>
                            <td><label>Laporan Operasi :</label></td>
                            <td> ${actionBean.operasiPenguatkuasaan.catatan}&nbsp;</td>
                        </tr>
                        </table>
            </div>
        </c:if>
        <div class="subtitle">
                <fieldset class="aras2">
                    <legend>
                        Maklumat Agensi Yang Terlibat
                    </legend>
                    <div class="content" align="center">
                       <display:table class="tablecloth" name="${actionBean.senaraiOperasiAgensi}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Agensi Yang Terlibat" property="agensi.nama"></display:column>
                            <display:column title="Alamat">${line.agensi.alamat1}
                        <c:if test="${line.agensi.alamat2 ne null}"> , </c:if>
                        ${line.agensi.alamat2}
                        <c:if test="${line.agensi.alamat3 ne null}"> , </c:if>
                        ${line.agensi.alamat3}
                        <c:if test="${line.agensi.alamat4 ne null}"> , </c:if>
                    ${line.agensi.alamat4}
                    <c:if test="${line.agensi.poskod ne null}"> , </c:if>
                    ${line.agensi.poskod}
                    <c:if test="${line.agensi.kodNegeri.nama ne null}"> , </c:if>
                    ${line.agensi.kodNegeri.nama}</display:column>
                   </display:table>
                    </div>
                </fieldset>
            </div>
    </div>
</s:form>