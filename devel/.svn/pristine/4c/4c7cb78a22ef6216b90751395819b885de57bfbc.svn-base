<%-- 
    Document   : laporanTanah_lotSempadan
    Created on : Jun 2, 2012, 3:18:30 PM
    Author     : Murali
--%>

<%@ page import="java.io.*,etanah.Configuration" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
    textarea.bolded {
        font-weight:bold;
    }
    textarea.italics {
        font-style:italic;
    }
</style>
<script type="text/javascript">   
    
    function validateLT(){
        if($("#tarikhSiasatan").val() == ""){
            alert('Sila masukkan " Tarikh Siasatan " terlebih dahulu.');
            $('#tarikhSiasatan').focus();
            return false;
        }
        if($("#kedudukanTanah").val() == " "){
            alert('Sila masukkan " Kedudukan Tanah " terlebih dahulu.');
            $("#kedudukanTanah").focus();
            return false;
        }
        if($("#keadaanTanah").val() == " "){
            alert('Sila masukkan " Keadaan Bangunan " terlebih dahulu.');
            $("#keadaanTanah").focus();
            return false;
        }
        if($("#syorPerakuan").val() == " "){
            alert('Sila masukkan " Syor dan Perakuan " terlebih dahulu.');
            $("#syorPerakuan").focus();
            return false;
        }
        return true;
    }    
    
    function validateSave(event, f){
        if(validateLT()){
            
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });          
            
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.get(url,q,
            function(data){
                $('#page_div',self.document).html(data);
                $.unblockUI();
            },'html');
            return true;
        }
    }
   
    function clearForm(){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });          
        var url = "${pageContext.request.contextPath}/strata/laporanTanah?resetForm";
        $.post(url,
        function(data){
            $('#page_div').html(data);
            $.unblockUI();
        },'html');
    }

    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });
  
</script>

<%--Added for image thumbNail--%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/images/up/plusimageviewer.css" />
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/images/up/jquery12.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/images/up/plusimageviewer.js"></script>
<%--End--%>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>  
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.strata.LaporanTanahMelakaActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1" >
            <legend >Laporan Tanah</legend> <br />            
            <p align="center">              
                <c:if test="${edit}">
                <table class="tablecloth">
                    <tr> 
                        <th style="width:20%" valign="top">
                            Tarikh Siasatan
                        </th>
                        <td>
                            <s:text name="tarikhSiasatan" id="tarikhSiasatan" formatPattern="dd/MM/yyyy" class="datepicker" ></s:text>
                            <font color="red">*</font>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:20%" valign="top">
                            Perihal Permohonan
                        </th>
                        <td>
                            <s:textarea name="perihalPermohonan" id="perihalPermohonan" cols="100" rows="5" class="normal_text"/>
                        </td>
                        <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHPP' || actionBean.permohonan.kodUrusan.kod eq 'PHPC'}">
                            <td id="perihalPermohonan" name="perihalPermohonan">
                                Permohonan telah diterima dari

                                <c:set value="1" var="count"/>
                                <c:forEach items="${actionBean.listHakmilikPihak}" var="senarai">
                                    <c:out value="${senarai.nama}"/>
                                    <c:if test="${count < fn:length(actionBean.listHakmilikPihak)}">
                                        ,
                                    </c:if>
                                    <c:set value="${count + 1}" var="count"/>
                                </c:forEach>

                                selaku tuan Tanah seluas lebih kurang 
                                <c:forEach items="${actionBean.permohonan.senaraiHakmilik}" var="senarai">
                                    <c:out value="${senarai.hakmilik.luas}"/>
                                </c:forEach>

                              ${actionBean.jenisluas}
                                 untuk tujuan bangunan kediaman di bawah seksyen 10 AHS 1985 (AKTA 318)
                                dan Kaedah-Kaedah Hakmilik Strata Melaka 1987
                            </td>
                        </c:if>--%>
                    </tr>
                    <tr>
                        <th style="width:20%"  valign="top">
                            Kedudukan Tanah
                        </th>
                        <td>
                            <s:textarea name="kedudukanTanah" id="kedudukanTanah" cols="100" rows="5" class="normal_text"/>
                            <font color="red">*</font>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:20%"  valign="top">
                            Keadaan Bangunan
                        </th>
                        <td>
                            <s:textarea name="keadaanTanah" id="keadaanTanah" cols="100" rows="5" class="normal_text"/>
                            <font color="red">*</font>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:20%"  valign="top">
                            Lain-lain Perkara
                        </th>
                        <td>
                            <s:textarea name="lainlainperkara" id="lainlainperkara" cols="100" rows="5" class="normal_text"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:20%"  align="left" valign="top" >
                            Syor dan Perakuan
                        </th>
                        <td>
                            <s:textarea name="syorPerakuan" id="syorPerakuan" cols="100" rows="5" class="normal_text"/>
                            <font color="red">*</font>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:20%" > &nbsp;</td>
                        <td>
                            <s:button class="btn" value="Simpan" name="saveLaporan" onclick="validateSave(this.name, this.form, 'page_div')"/>
                            <s:button class="btn" value="Isi Semula" name="resetForm" onclick="clearForm()"/>
                        </td>
                    </tr>
                </table>
            </c:if>

            <c:if test="${!edit}">
                <table class="tablecloth">
                    <tr>
                        <th style="width:20%" valign="top">
                            Tarikh Siasatan
                        </th>
                        <td>
                            <s:text readonly="true" name="tarikhSiasatan" id="tarikhSiasatan" formatPattern="dd/MM/yyyy" class="datepicker" ></s:text>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:20%" valign="top">
                            Perihal Permohonan
                        </th>
                        <td>
                            <s:textarea readonly="true" name="perihalPermohonan" id="perihalPermohonan" cols="100" rows="5" class="normal_text"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:20%"  valign="top">
                            Kedudukan Tanah
                        </th>
                        <td>
                            <s:textarea readonly="true" name="kedudukanTanah" id="kedudukanTanah" cols="100" rows="5" class="normal_text"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:20%"  valign="top">
                            Keadaan Bangunan
                        </th>
                        <td>
                            <s:textarea readonly="true" name="keadaanTanah" id="keadaanTanah" cols="100" rows="5" class="normal_text"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:20%"  valign="top">
                            Lain-lain Perkara
                        </th>
                        <td>
                            <s:textarea readonly="true" name="lainlainperkara" id="lainlainperkara" cols="100" rows="5" class="normal_text"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:20%"  align="left" valign="top" >
                            Syor dan Perakuan
                        </th>
                        <td>
                            <s:textarea readonly="true" name="syorPerakuan" id="syorPerakuan" cols="100" rows="5" class="normal_text"/>
                        </td>
                    </tr>
                </table>
            </c:if>
    </div>
</s:form>
