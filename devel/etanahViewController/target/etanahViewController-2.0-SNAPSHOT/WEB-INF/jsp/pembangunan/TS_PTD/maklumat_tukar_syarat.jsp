<%--
    Document   : maklumat_tukar_syarat
    Created on : Mar 8, 2010, 11:07:38 AM
    Author     : nursyazwani
   Modified By : NageswaraRao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:left;        
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
    }
</style>

<script type = "text/javascript">
    $(document).ready( function() {
        var bng = '${actionBean.gunaBaru}';
        //alert(bng);
        if(bng == '0'){
            $('#catg0').show();
            $('#catg1').hide(); 
            $('#catg2').hide(); 
            $('#catg3').hide(); 
            $('#catg4').hide(); 
            $('#catg5').hide(); 
            $('#catg8').hide(); 
            $('#catg11').hide(); 
        }else if(bng == '1'){
            $('#catg0').hide();
            $('#catg1').show(); 
            $('#catg2').hide(); 
            $('#catg3').hide(); 
            $('#catg4').hide(); 
            $('#catg5').hide(); 
            $('#catg8').hide(); 
            $('#catg11').hide(); 
        }else if(bng == '2'){
            $('#catg0').hide();
            $('#catg1').hide(); 
            $('#catg2').show(); 
            $('#catg3').hide(); 
            $('#catg4').hide(); 
            $('#catg5').hide(); 
            $('#catg8').hide(); 
            $('#catg11').hide(); 
        }else if(bng == '3'){
            $('#catg0').hide();
            $('#catg1').hide(); 
            $('#catg2').hide(); 
            $('#catg3').show(); 
            $('#catg4').hide(); 
            $('#catg5').hide(); 
            $('#catg8').hide(); 
            $('#catg11').hide(); 
        }else if(bng == '4'){
            $('#catg0').hide();
            $('#catg1').hide(); 
            $('#catg2').hide(); 
            $('#catg3').hide(); 
            $('#catg4').show(); 
            $('#catg5').hide(); 
            $('#catg8').hide(); 
            $('#catg11').hide(); 
        }else if(bng == '5'){
            $('#catg0').hide();
            $('#catg1').hide(); 
            $('#catg2').hide(); 
            $('#catg3').hide(); 
            $('#catg4').hide(); 
            $('#catg5').show(); 
            $('#catg8').hide(); 
            $('#catg11').hide(); 
        }else if(bng == '8'){
            $('#catg0').hide();
            $('#catg1').hide(); 
            $('#catg2').hide(); 
            $('#catg3').hide(); 
            $('#catg4').hide(); 
            $('#catg5').hide(); 
            $('#catg8').show(); 
            $('#catg11').hide(); 
        }else if(bng == '11'){
            $('#catg0').hide();
            $('#catg1').hide(); 
            $('#catg2').hide(); 
            $('#catg3').hide(); 
            $('#catg4').hide(); 
            $('#catg5').hide(); 
            $('#catg8').hide(); 
            $('#catg11').show(); 
        }else {
            $('#catg0').hide();
            $('#catg1').hide(); 
            $('#catg2').hide(); 
            $('#catg3').hide(); 
            $('#catg4').hide(); 
            $('#catg5').hide(); 
            $('#catg8').hide(); 
            $('#catg11').hide(); 
        }
   
    });   
    
    
    function searchKodSyaratNyata(index){
        var url = '${pageContext.request.contextPath}/pembangunan/carianSyaratSekatan?showFormCariKodSyaratNyata2&index='+index;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
    }
   
    function searchKodSekatan(index){
        var url = '${pageContext.request.contextPath}/pembangunan/carianSyaratSekatan?showFormCariKodSekatan&index='+index;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
    }

    <%-- function searchKodSekatan(){
        var url = '${pageContext.request.contextPath}/pembangunan/maklumat_tukarSyarat?kodSekatanPopup';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }--%>

    <%--function searchKodSyaratNyata(index){
        var url = '${pageContext.request.contextPath}/pembangunan/kertas_mmk?kodSyaratNyataPopup&index='+index;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }--%>
        
        function doSomething() {
            var kkt = $('#kodkatTanah').val();            
            if(kkt == '0'){
                $('#catg0').show();
                $('#catg1').hide(); 
                $('#catg2').hide(); 
                $('#catg3').hide(); 
                $('#catg4').hide(); 
                $('#catg5').hide(); 
                $('#catg8').hide(); 
                $('#catg11').hide(); 
            }           
            if(kkt == '1'){
                $('#catg0').hide();
                $('#catg1').show(); 
                $('#catg2').hide(); 
                $('#catg3').hide(); 
                $('#catg4').hide(); 
                $('#catg5').hide(); 
                $('#catg8').hide(); 
                $('#catg11').hide(); 
            }
            if(kkt == '2'){
                $('#catg0').hide();
                $('#catg1').hide(); 
                $('#catg2').show(); 
                $('#catg3').hide(); 
                $('#catg4').hide(); 
                $('#catg5').hide(); 
                $('#catg8').hide(); 
                $('#catg11').hide(); 
            }
            if(kkt == '3'){
                $('#catg0').hide();
                $('#catg1').hide(); 
                $('#catg2').hide(); 
                $('#catg3').show(); 
                $('#catg4').hide(); 
                $('#catg5').hide(); 
                $('#catg8').hide(); 
                $('#catg11').hide(); 
            }
            if(kkt == '4'){
                $('#catg0').hide();
                $('#catg1').hide(); 
                $('#catg2').hide(); 
                $('#catg3').hide(); 
                $('#catg4').show(); 
                $('#catg5').hide(); 
                $('#catg8').hide(); 
                $('#catg11').hide(); 
            }
            if(kkt == '5'){
                $('#catg0').hide();
                $('#catg1').hide(); 
                $('#catg2').hide(); 
                $('#catg3').hide(); 
                $('#catg4').hide(); 
                $('#catg5').show(); 
                $('#catg8').hide(); 
                $('#catg11').hide(); 
            }
            if(kkt == '8'){
                $('#catg0').hide();
                $('#catg1').hide(); 
                $('#catg2').hide(); 
                $('#catg3').hide(); 
                $('#catg4').hide(); 
                $('#catg5').hide(); 
                $('#catg8').show(); 
                $('#catg11').hide(); 
            }
            if(kkt == '11'){
                $('#catg0').hide();
                $('#catg1').hide(); 
                $('#catg2').hide(); 
                $('#catg3').hide(); 
                $('#catg4').hide(); 
                $('#catg5').hide(); 
                $('#catg8').hide(); 
                $('#catg11').show(); 
            }
        }

</script>
<s:form beanclass="etanah.view.stripes.pembangunan.MaklumatTukarSyaratActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Untuk Mengubah Jenis Penggunaan Tanah, Syarat-Syarat Nyata Atau Sekatan Kepentingan
            </legend>
            <div class="content" align="center">
                <table border="0" width="80%" cellspacing="15">
                    <c:set value="i" var="0"/>
                    <c:if test="${edit}">                        
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSKKT' || actionBean.permohonan.kodUrusan.kod eq 'TSKSN' || actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                            <c:set var="i" value="${i+1}"/>
                            <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> ${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">  Diubah Kategori Tanah  </font></td></tr>

                            <tr><td></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> Dari: </font><b> ${actionBean.hakmilik.kategoriTanah.nama}</b> &nbsp;&nbsp;&nbsp;
                                    <font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> kepada : </font>
                                    <s:select name="gunaBaru" id="kodkatTanah" onchange="doSomething(this.form);">
                                        <s:option value="">--Sila Pilih--</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod"/>
                                    </s:select>
                                </td></tr>
                            </c:if>
                            <c:set var="i" value="${i+1}"/>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSKKT' || actionBean.permohonan.kodUrusan.kod eq 'TSKSN' || actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS' || actionBean.permohonan.kodUrusan.kod eq 'TSPSN'}">
                            <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> ${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">  
                                        <c:if test = "${actionBean.permohonan.kodUrusan.kod eq 'TSPSN'}">
                                            Kegunaan Tanah Baru (Untuk Kiraan Cukai) :
                                        </c:if>
                                        <c:if test = "${actionBean.permohonan.kodUrusan.kod ne 'TSPSN'}">
                                            Dikenakan Guna Tanah (Untuk Kiraan Cukai) :
                                        </c:if>
                                        <div id="catg0">
                                            <s:select name="kenaGuna" value="${actionBean.kenaGuna}">
                                                <s:option value="">--Sila Pilih--</s:option>
                                                <s:options-collection collection="${actionBean.senaraiKodKegunaanTanahs0}" label="nama" value="kod" />
                                            </s:select>
                                        </div>                                        
                                        <div id="catg1">
                                            <s:select name="kenaGuna" value="${actionBean.kenaGuna}">
                                                <s:option value="">--Sila Pilih--</s:option>
                                                <s:options-collection collection="${actionBean.senaraiKodKegunaanTanahs1}" label="nama" value="kod" />
                                            </s:select>
                                        </div>
                                        <div id="catg2">
                                            <s:select name="kenaGuna" value="${actionBean.kenaGuna}">
                                                <s:option value="">--Sila Pilih--</s:option>
                                                <s:options-collection collection="${actionBean.senaraiKodKegunaanTanahs2}" label="nama" value="kod" />
                                            </s:select>
                                        </div>
                                        <div id="catg3">
                                            <s:select name="kenaGuna" value="${actionBean.kenaGuna}">
                                                <s:option value="">--Sila Pilih--</s:option>
                                                <s:options-collection collection="${actionBean.senaraiKodKegunaanTanahs3}" label="nama" value="kod" />
                                            </s:select>
                                        </div>
                                        <div id="catg4">
                                            <s:select name="kenaGuna" value="${actionBean.kenaGuna}">
                                                <s:option value="">--Sila Pilih--</s:option>
                                                <s:options-collection collection="${actionBean.senaraiKodKegunaanTanahs4}" label="nama" value="kod" />
                                            </s:select>
                                        </div>
                                        <div id="catg5">
                                            <s:select name="kenaGuna" value="${actionBean.kenaGuna}">
                                                <s:option value="">--Sila Pilih--</s:option>
                                                <s:options-collection collection="${actionBean.senaraiKodKegunaanTanahs5}" label="nama" value="kod" />
                                            </s:select>
                                        </div>
                                        <div id="catg8">
                                            <s:select name="kenaGuna" value="${actionBean.kenaGuna}">
                                                <s:option value="">--Sila Pilih--</s:option>
                                                <s:options-collection collection="${actionBean.senaraiKodKegunaanTanahs8}" label="nama" value="kod" />
                                            </s:select>
                                        </div>
                                        <div id="catg11">
                                            <s:select name="kenaGuna" value="${actionBean.kenaGuna}">
                                                <s:option value="">--Sila Pilih--</s:option>
                                                <s:options-collection collection="${actionBean.senaraiKodKegunaanTanahs11}" label="nama" value="kod" />
                                            </s:select>
                                        </div>                                        
                                    </font></td></tr>
                                </c:if>
                                <%--</c:if>--%>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN'}">
                                    <c:set var="i" value="${i+1}"/>
                            <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> ${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">  Dibatalkan ungkapan "Padi","Getah","Kampung",dsb :
                                    </font>  <s:text name="batalUngakapan" size="69" class="normal_text"/>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN'|| actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                                    <c:set var="i" value="${i+1}"/>
                            <tr><td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> ${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">
                                        Dibatalkan syarat nyata :</font><b> ${actionBean.hakmilik.syaratNyata.syarat}</b>
                                </td></tr>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSN'||actionBean.permohonan.kodUrusan.kod eq 'TSKSN'|| actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'|| actionBean.permohonan.kodUrusan.kod eq 'TSKKT'}">
                                <c:set var="i" value="${i+1}"/>
                            <tr><td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> ${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">
                                        Dipinda syarat nyata : </font><b>${actionBean.hakmilik.syaratNyata.syarat}</b>
                                </td></tr>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN'||actionBean.permohonan.kodUrusan.kod eq 'TSPSN' || actionBean.permohonan.kodUrusan.kod eq 'TSKSN' || actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'|| actionBean.permohonan.kodUrusan.kod eq 'TSKKT'}">
                            <tr><td></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> 
                                        Supaya menjadi seperti berikut :</font></td></tr>                               
                            <tr><td></td>
                                <td><%--<b> ${actionBean.hp.syaratNyataBaru.syarat} </b>--%>
                                    <s:textarea name="kodSyaratNyataBaru1" id="syaratNyata1" readonly="readonly" rows="5" cols="120" value="${actionBean.hp.syaratNyataBaru.kod} - ${actionBean.hp.syaratNyataBaru.syarat}"  /></td></tr>

                            <tr><td></td>
                                <td><s:hidden name="syaratBaru1" id="kod1" value="${actionBean.hp.syaratNyataBaru.kod}" />
                                    <s:button class="btn" value="Cari" name="add" onclick="javascript:searchKodSyaratNyata(1)" />
                                </td></tr>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBSK' || actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                                <c:set var="i" value="${i+1}"/>
                            <tr><td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> ${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">
                                        Dibatalkan sekatan kepentingan : </font>
                                        <c:if test = "${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}">
                                        <b> ${actionBean.hakmilik.sekatanKepentingan.sekatan}</b>
                                    </c:if>
                                    <c:if test = "${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}">
                                        <b> - </b>
                                    </c:if>
                                </td></tr>
                                <c:set var="i" value="${i+1}"/>
                            <tr><td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> ${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">
                                        Dipinda sekatan kepentingan :</font>
                                        <c:if test = "${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}">
                                        <b> ${actionBean.hakmilik.sekatanKepentingan.sekatan}</b>
                                    </c:if>
                                    <c:if test = "${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}">
                                        <b> - </b>
                                    </c:if>
                                </td></tr>

                            <tr><td></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">
                                        Supaya menjadi seperti berikut :</font> </td></tr>                                
                            <tr><td></td>
                                <td><%--<b>${actionBean.hp.sekatanKepentinganBaru.sekatan}</b>--%>
                                    <s:textarea name="kodSekatanKepentinganBaru12" readonly="readonly" id="sekatan1" rows="5" cols="120" value="${actionBean.hp.sekatanKepentinganBaru.kod} - ${actionBean.hp.sekatanKepentinganBaru.sekatan}" /></td></tr>

                            <%--<s:textarea name="kodSekatanKepentinganBaru12" id="sekatan1" value="${actionBean.hp.sekatanKepentinganBaru.sekatan}" readonly="readonly" rows="5" cols="100" class="textBesa"/>--%>
                            <tr><td></td>
                                <td><s:hidden name="sekatanBaru1"  id="kodSktn1"  value="${actionBean.hp.sekatanKepentinganBaru.kod}" />
                                    <s:button class="btn" value="Cari" name="add" onclick="javascript:searchKodSekatan(1)" />
                                </td></tr>
                            </c:if>
                        </c:if>
                </table>
                <c:if test="${!edit}">
                    <table border="0" width="80%" cellspacing="15">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSKKT' || actionBean.permohonan.kodUrusan.kod eq 'TSKSN'|| actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                            <c:set var="i" value="${i+1}"/>
                            <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">  Diubah Kategori Tanah  </font></td></tr>

                            <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> </font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> Dari: </font><b> ${actionBean.hakmilik.kategoriTanah.nama}</b> &nbsp;&nbsp;&nbsp;
                                    <font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> kepada : </font>
                                    <b>${actionBean.hp.kategoriTanahBaru.nama}</b>
                                </td></tr>
                                <c:set var="i" value="${i+1}"/>
                            <tr><td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">  Dikenakan Guna Tanah (Untuk Kiraan Cukai) :
                                        <%--</font><b>${actionBean.hp.kategoriTanahBaru.nama}</b></td></tr>--%>
                                    </font><b>${actionBean.hp.kodKegunaanTanah.nama}</b></td></tr>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN'}">
                                    <c:set var="i" value="${i+1}"/>
                            <tr><td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> ${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">  Dibatalkan ungkapan "Padi","Getah","Kampung",dsb :
                                    </font><b>${actionBean.hp.catatan}</b><td></tr>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN' || actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                                    <c:set var="i" value="${i+1}"/>
                            <tr><td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">
                                        Dibatalkan syarat nyata :</font><b> ${actionBean.hakmilik.syaratNyata.syarat}</b>
                                </td></tr>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSN' ||actionBean.permohonan.kodUrusan.kod eq 'TSKSN'|| actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'|| actionBean.permohonan.kodUrusan.kod eq 'TSKKT'}">
                                <c:set var="i" value="${i+1}"/>
                            <tr><td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">
                                        Dipinda syarat nyata : </font><b>${actionBean.hakmilik.syaratNyata.syarat}</b>
                                </td></tr>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN' ||actionBean.permohonan.kodUrusan.kod eq 'TSKSN'|| actionBean.permohonan.kodUrusan.kod eq 'TSPSN' || actionBean.permohonan.kodUrusan.kod eq 'TSKSN' || actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'|| actionBean.permohonan.kodUrusan.kod eq 'TSKKT'}">
                            <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> </font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> 
                                        Supaya menjadi seperti berikut :                               
                                    </font><b><%--${actionBean.hp.syaratNyataBaru.kod} ---%> ${actionBean.hp.syaratNyataBaru.syarat}</b></td></tr>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBSK' || actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                                    <c:set var="i" value="${i+1}"/>
                            <tr><td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">
                                        Dibatalkan sekatan kepentingan : </font>                              
                                        <c:if test = "${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}">
                                        <b> ${actionBean.hakmilik.sekatanKepentingan.sekatan}</b>
                                    </c:if>
                                    <c:if test = "${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}">
                                        <b> - </b>
                                    </c:if>
                                </td></tr>

                            <c:set var="i" value="${i+1}"/>
                            <tr><td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">
                                        Dipinda sekatan kepentingan :</font>
                                        <c:if test = "${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}">
                                        <b> ${actionBean.hakmilik.sekatanKepentingan.sekatan}</b>
                                    </c:if>
                                    <c:if test = "${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}">
                                        <b> - </b>
                                    </c:if>                                  
                                </td></tr>

                            <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> </font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> 
                                        Supaya menjadi seperti berikut :                                
                                    </font><b><%--${actionBean.hp.sekatanKepentinganBaru.kod} ---%> ${actionBean.hp.sekatanKepentinganBaru.sekatan}</b></td></tr>
                                </c:if>
                    </table>
                </c:if>
                <p align="center">
                    <c:if test="${edit}">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                </p>
            </div>
        </fieldset>
    </div>
</s:form>
