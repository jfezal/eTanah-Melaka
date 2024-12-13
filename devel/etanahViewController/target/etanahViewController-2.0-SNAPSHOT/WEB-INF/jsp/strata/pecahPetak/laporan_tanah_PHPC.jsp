<%-- 
    Document   : laporanTanah_PHPC
    Created on : Oct 6, 2012, 3:18:30 PM
    Author     : Murali
--%>

<%@ page import="java.io.*,etanah.Configuration" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
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
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.get(url,q,
        function(data){
            $('#page_div',self.document).html(data);
        },'html');
    }
 
    function clearForm(){   
        var val = $("#idMh").val();
    <%--var url = "${pageContext.request.contextPath}/strata/laporanTanah_PHPC?resetForm";--%>            
            var url = '${pageContext.request.contextPath}/strata/laporanTanah_PHPC?resetForm&idMh=' + val;
            $.post(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        $(document).ready(function() {
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        });

        function refreshPage(){
            var url = '${pageContext.request.contextPath}/strata/laporanTanah_PHPC?reload';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        function muatNaikimej() {
            var val = $("#idMh").val();
            var left = (screen.width/2)-(1000/2);
            var top = (screen.height/2)-(150/2);
            var url = '${pageContext.request.contextPath}/strata/laporanTanah_PHPC?uploadDoc&idMh='+ val;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=300, left=" + left + ",top=" + top);
        }
        function hapusimej(idDokumen){
            if((confirm("Anda pasti ingin menghapuskan imej ini?"))){
                var url = '${pageContext.request.contextPath}/strata/laporanTanah_PHPC?hapusImej&idDokumen='+idDokumen;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }
        function hakdetails (val) {
            <%--alert(val);--%>
            doBlockUI();
            var url = '${pageContext.request.contextPath}/strata/laporanTanah_PHPC?hakdetails&idMh=' + val;
            $.ajax({
                type:"GET",
                url : url,
                dataType : 'html',
                error : function (xhr, ajaxOptions, thrownError){
                    alert("error=" + xhr.responseText);
                    doUnBlockUI();
                },
                success : function(data){
                    $('#page_div').html(data);
                    doUnBlockUI();
                }
            });
        }
  
</script>

<%--Added for image thumbNail--%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/images/up/plusimageviewer.css" />

<%--<script type="text/javascript" src="${pageContext.request.contextPath}/images/up/jquery12.js"></script>--%>

<script type="text/javascript" src="${pageContext.request.contextPath}/images/up/plusimageviewer.js"></script>

<%--End--%>


<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.strata.LaporanTanahPHPCMelakaActionBean">
    <s:messages/>
    <s:errors/> <br /><br />    
    <div class="subtitle">
        <fieldset class="aras1" >
            <legend >Senerai Hakmilik Terlibat</legend> <br />
            <div align="center">
                <font color="#003194" style="font-weight: bold; font-family:Tahoma; font-size: 13px;">
                    Hakmilik :
                </font>               
                <s:select name="idMh" onchange="hakdetails(this.value);" id="idMh">
                    <c:forEach items="${actionBean.senaraiHakmilikPermohonan}" var="item" varStatus="line">
                        <s:option value="${item.id}" style="width:400px">
                            ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                        </s:option>
                    </c:forEach>
                </s:select>
            </div> 
        </fieldset>
    </div> <br /><br />
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
                        </td>
                    </tr>
                    <tr>
                        <th style="width:20%" valign="top">
                            Perihal Permohonan
                        </th>
                        <td>
                            <s:textarea name="perihalPermohonan" id="perihalPermohonan" cols="100" rows="5" class="normal_text"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:20%"  valign="top">
                            Kedudukan Tanah
                        </th>
                        <td>
                            <s:textarea name="kedudukanTanah" id="kedudukanTanah" cols="100" rows="5" class="normal_text"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:20%"  valign="top">
                            Keadaan Bangunan
                        </th>
                        <td>
                            <s:textarea name="keadaanTanah" id="keadaanTanah" cols="100" rows="5" class="normal_text"/>
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

                        </td>
                    </tr>                   
                </table> <br /><br />

                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>Laporan Sempadan</legend>
                        <s:hidden name="lokasi" id="lokasi"/>
                    </fieldset></div>

                <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>&nbsp;Sempadan</th><th>Nombor Lot</th><th>Jenis Kegunaan Tanah</th>
                        </tr>
                        <tr>
                            <th>
                                Utara
                            </th>

                            <td>
                                <s:textarea name="lotUtara" class="normal_text" cols="37" rows="3" />
                            </td>
                            <td>
                                <s:textarea name="gunaUtara" id="gunaUtara" cols="50" rows="3" class="normal_text"/>
                            </td>

                        </tr>
                        <tr>
                            <th>
                                Selatan
                            </th>

                            <td>
                                <s:textarea name="lotSelatan" class="normal_text" cols="37" rows="3" />
                            </td>
                            <td>
                                <s:textarea name="gunaSelatan" id="gunaSelatan" cols="50" rows="3" class="normal_text"/>
                            </td>

                        </tr>
                        <tr>
                            <th>
                                Timur
                            </th>

                            <td>
                                <s:textarea name="lotTimur" class="normal_text" cols="37" rows="3"/>
                            </td>
                            <td>
                                <s:textarea name="gunaTimur" id="gunaTimur" cols="50" rows="3" class="normal_text"/>
                            </td>                            
                        </tr><tr>
                            <th>
                                Barat
                            </th>                           
                            <td>
                                <s:textarea name="lotBarat" class="normal_text" cols="37" rows="3"/>
                            </td>
                            <td>
                                <s:textarea name="gunaBarat" id="gunaBarat" cols="50" rows="3" class="normal_text"/>
                            </td>                          
                        </tr>
                    </table>
                    <%-- <s:button name="simpanSempadan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                     <s:button class="btn" value="Isi Semula" name="resetForm" onclick="clearForm()"/>--%>
                </div> <br /><br />
                <table align="center" border="0" width="80%">
                    <tr>
                        <td style="width:50%" > &nbsp;</td>
                        <td>  <s:button class="btn" value="Simpan" name="saveLaporan" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            <s:button class="btn" value="Isi Semula" name="resetForm" onclick="clearForm();"/>
                        </td>
                    </tr>
                </table> <br /><br />
                <%--imej--%>
                <s:hidden name="idPermohonan" id="idmohon"/>
                <s:hidden name="imageFolderPath" id="lokasi"/>
                <div class="subtitle">
                    <c:set var="filePath"  value="${actionBean.imageFolderPath}" />
                    <fieldset class="aras1">
                        <legend>Muat naik Imej Laporan</legend>
                        <%--<c:if test="${edit}">--%>
                        <p>
                            <font color="red">*</font> Sila klik 'Muatnaik imej' untuk muatnaik imej <br>
                            <font color="red">*</font> klik 'Hapus Imej' untuk hapus imej
                        </p><br>
                        <p>
                            <s:button name="muatnaikimej" value="Muatnaik imej" class="longbtn"  onclick="muatNaikimej();" /></p>
                            <%--</c:if>--%>
                        <br>
                        <%
                                    String file = "C:/" + (String) pageContext.getAttribute("filePath");
                                    File f = new File(file);
                                    String[] fileNames = f.list();
                                    File[] fileObjects = f.listFiles();
                        %>
                        <table cellspacing="20" align="center">
                            <c:choose>
                                <c:when test="${fn:length(actionBean.senaraiImejLaporan) > 0}">
                                    <c:forEach items="${actionBean.senaraiImejLaporan}" var="item" varStatus="loop">
                                        <%--<font color="black" size="3">${loop.count} . ${item.dokumen.kodDokumen.nama} </font><br/>--%>
                                        <c:if test="${loop.count mod 8 eq 0}">
                                            <tr >
                                            </c:if>
                                            <td>
                                                <%--<c:if test="${edit}">--%>
                                                <s:button name="hapusImej" value="Hapus Imej" class="btn"onclick="hapusimej('${item.dokumen.idDokumen}');"/><br/>
                                                <%--</c:if>--%>
                                                <img alt="Imej" src="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" height="110" width="109" data-plusimage="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" />
                                                <br>
                                                ${item.catatan}
                                            </td>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                    <tr>
                                        <td><font color="red">Tiada Imej Imbasan.</font></td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </table>
                    </fieldset>
                </div>
            </c:if>

            <c:if test="${!edit}">
                <table class="tablecloth">
                    <!--                <tr><th align="left" >   Perihal Permohonan</th></tr>-->
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

                <%--sempadan--%>

                <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>&nbsp;Sempadan</th><th>Nombor Lot</th><th>Jenis Kegunaan Tanah</th>
                        </tr>
                        <tr>
                            <th>
                                Utara
                            </th>
                            <td>
                                <s:textarea name="lotUtara" class="normal_text" cols="30" rows="3" readonly="true" />
                            </td>
                            <td>
                                <s:textarea name="gunaUtara" id="gunaUtara" cols="50" rows="3" class="normal_text" readonly="true"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Selatan
                            </th>
                            <td>
                                <s:textarea name="lotSelatan" class="normal_text" cols="30" rows="3" readonly="true" />
                            </td>
                            <td>
                                <s:textarea name="gunaSelatan" id="gunaSelatan" cols="50" rows="3" class="normal_text" readonly="true"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Timur
                            </th>
                            <td>
                                <s:textarea name="lotTimur" class="normal_text" cols="30" rows="3" readonly="true"/>
                            </td>
                            <td>
                                <s:textarea name="gunaTimur" id="gunaTimur" cols="50" rows="3" class="normal_text" readonly="true"/>
                            </td>
                        </tr><tr>
                            <th>
                                Barat
                            </th>
                            <td>
                                <s:textarea name="lotBarat" class="normal_text" cols="30" rows="3" readonly="true"/>
                            </td>
                            <td>
                                <s:textarea name="gunaBarat" id="gunaBarat" cols="50" rows="3" class="normal_text" readonly="true"/>
                            </td>
                        </tr>
                    </table>
                </div>
                <%--imej--%>
                <s:hidden name="idPermohonan" id="idmohon"/>
                <s:hidden name="imageFolderPath" id="lokasi"/>
                <div class="subtitle">
                    <c:set var="filePath"  value="${actionBean.imageFolderPath}" />
                    <fieldset class="aras1">
                        <legend>Muat naik Imej Laporan</legend>                       
                        <br>
                        <table cellspacing="20" align="center">
                            <c:choose>
                                <c:when test="${fn:length(actionBean.senaraiImejLaporan) > 0}">
                                    <c:forEach items="${actionBean.senaraiImejLaporan}" var="item" varStatus="loop">
                                        <c:if test="${loop.count mod 8 eq 0}">
                                            <tr >
                                            </c:if>
                                            <td>                                               
                                                <img alt="Imej" src="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" height="110" width="109" data-plusimage="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" />
                                                <br>
                                                ${item.catatan}
                                            </td>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                    <tr>
                                        <td><font color="red">Tiada Imej Imbasan.</font></td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </table>
                    </fieldset>
                </div>
            </c:if>
        </fieldset>
    </div>
</s:form>
