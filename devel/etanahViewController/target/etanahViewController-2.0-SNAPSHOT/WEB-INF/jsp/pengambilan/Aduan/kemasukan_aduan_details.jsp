<%-- 
    Document   : kemasukan_aduan_details
    Created on : May 28, 2013, 6:18:22 PM
    Author     : Admin
--%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<script type="text/javascript">


    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

        //get id pihak after kembali
        var idPihakVal = $("#idPihakVal").val();
        var bil =  ${fn:length(actionBean.hakmilikPermohonan.hakmilik.senaraiPihakBerkepentingan)};
        for (var i = 0; i < bil; i++){
    <%--alert("1 : "+$("#selectedPihak2"+i).val());--%>
    <%--alert("2 : "+idPihakVal);--%>
                if($("#selectedPihak2"+i).val() == idPihakVal){
    <%--alert("sama");--%>
                    document.getElementById("selectedPihak2"+i).checked = true;
                }
            }

        });
        function save(){
            self.opener.refreshPageTanahRizab();
            self.close();}

        function validation() {
            if($("#idhakmilik").val() == ""){
                alert('Sila masukkan " No H/M " terlebih dahulu.');
                $("#idhakmilik").focus();
                return true;
            }
            if($("#nama").val() == ""){
                alert('Sila masukkan " Nama " terlebih dahulu.');
                $("#nama").focus();
                return true;
            }

            if($("#kp").val() == ""){
                alert('Sila masukkan " No. Pengenalan " terlebih dahulu.');
                $("#kp").focus();
                return true;
            }
            if($("#alamat1").val() == ""){
                alert('Sila masukkan " No. Pengenalan " terlebih dahulu.');
                $("#alamat1").focus();
                return true;
            }

            if($("#bandar").val() == ""){
                alert('Sila masukkan " Bandar " terlebih dahulu.');
                $("#bandar").focus();
                return true;
            }
            if($("#poskod").val() == ""){
                alert('Sila masukkan " Poskod " terlebih dahulu.');
                $("#poskod").focus();
                return true;
            }
            if($("#negeri").val() == ""){
                alert('Sila masukkan " Negeri " terlebih dahulu.');
                $("#negeri").focus();
                return true;
            }
            if($("#noTelefon").val() == ""){
                alert('Sila masukkan " No. Telefon " terlebih dahulu.');
                $("#noTelefon").focus();
                return true;
            }
        }

        function savePenjaga(event, f){
        
            if(validation()){

            }else{
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.opener.refreshPagePBT();
    <%--self.opener.refreshPageHakmilik();--%>
                    self.close();
                },'html');
            }
        }
        $(document).ready(function() {
            document.getElementById("hiddenWakil").style.visibility = 'hidden';
            document.getElementById("hiddenWakil").style.display = 'none';
            $("#close").click( function(){
                setTimeout(function(){
                    self.close();
                }, 100);
            });
        });

        function showWakil(){
           document.getElementById("hiddenWakil").style.visibility = 'visible';
           document.getElementById("hiddenWakil").style.display = '';
        }
        function selectRadio(obj){
    <%--alert("amik value");--%>
            $("#selectedPihak").val(obj.value);
    <%--var idH = $("#selectedPihak").val();--%>
    <%--alert(obj);--%>
        }

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div class="subtitle">

    <s:form beanclass="etanah.view.stripes.pengambilan.AduanActionBean">
        <s:messages/>
        <%--<s:hidden name="idPermohonanPhkTdkBerptg" value="${actionBean.idPermohonanPhkTdkBerptg}"/>--%>
        <s:hidden name="typesender" value="phtbedit"/>
        <s:hidden id="selectedPihak" name="selectedPihak"/>
        <s:hidden id="idMH" name="idMH" value="${actionBean.hakmilikPermohonan.id}"/>
        <s:hidden id="selectedMH" name="selectedMH" value="${actionBean.hakmilikPermohonan.id}"/>
        <s:hidden name="idPihakVal" id="idPihakVal"/>
        <s:hidden name="permohonan.idPermohonan"/>
        <s:hidden id="selectedWakil" name="selectedWakil"/>
        <%--<s:hidden name="permohonan.idPermohonan"/>
        <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.idPermohonan}"/>--%>

        <fieldset class="aras1">
            <%--<c:if test="${edit}">--%>
            <legend>Maklumat Permohonan</legend>
            <div class="subtitle" id="main">
                <div class="content" align="center">
                    <%--<s:hidden name="idPihak" id="idPihak" value="pihak.idPihak"/>--%>
                    <%--${actionBean.pihak.idPihak}--%>
                    <table class="tablecloth" border="0" width="75%">
                        <tr>
                            <td>
                                No. Perserahan/Permohonan :
                            </td>
                            <td>
                                ${actionBean.hakmilikPermohonan.permohonan.idPermohonan}
                            </td>
                        </tr>
                        <tr>
                            <td>Nama Permohonan :</td>
                            <td>
                                <font>${actionBean.hakmilikPermohonan.permohonan.kodUrusan.nama}</font>
                            </td>
                        </tr>
                        <tr>
                            <td>Tarikh Permohonan :</td>
                            <td>
                                <fmt:formatDate value="${actionBean.hakmilikPermohonan.permohonan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Agensi Pemohon :</td>
                            <td>
                                
                                ${actionBean.hakmilikPermohonan.permohonan.senaraiPemohon[0].pihak.nama}
                            </td>
                        </tr>
                        <tr>
                            <td>Alamat :</td>
                            <td>
                                ${actionBean.hakmilikPermohonan.permohonan.senaraiPemohon[0].pihak.alamat1}
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>
                                ${actionBean.hakmilikPermohonan.permohonan.senaraiPemohon[0].pihak.alamat2}
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>
                                ${actionBean.hakmilikPermohonan.permohonan.senaraiPemohon[0].pihak.alamat3}
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>
                                ${actionBean.hakmilikPermohonan.permohonan.senaraiPemohon[0].pihak.alamat4}
                            </td>
                        </tr>
                        <tr>
                            <td>Poskod :</td>
                            <td>
                                ${actionBean.hakmilikPermohonan.permohonan.senaraiPemohon[0].pihak.poskod}
                            </td>
                        </tr>
                        <tr>
                            <td>Negeri :</td>
                            <td>
                                ${actionBean.hakmilikPermohonan.permohonan.senaraiPemohon[0].pihak.negeri.nama}
                            </td>
                        </tr>
                        <tr>
                            <td>No Telefon :</td>
                            <td>
                                ${actionBean.hakmilikPermohonan.permohonan.senaraiPemohon[0].pihak.noTelefon1}
                            </td>
                        </tr>
                        <tr>
                            <td>Emel :</td>
                            <td>
                                ${actionBean.hakmilikPermohonan.permohonan.senaraiPemohon[0].pihak.email}
                            </td>
                        </tr>
                    </table>
                </div>
                <br/>
            </div>
            <br/>
        </fieldset>
        <br>
        <fieldset class="aras1">
            <legend>Maklumat Aduan</legend>
            <div class="subtitle" id="main">
                <div class="content" align="center">
                    <%--<s:hidden name="idPihak" id="idPihak" value="pihak.idPihak"/>--%>
                    <%--${actionBean.pihak.idPihak}--%>
                    <table class="tablecloth" border="0" width="75%">
                        <tr>
                            <td>
                                Perihal Aduan :
                            </td>
                            <td>
                                <s:textarea name="perihal" value="" rows="5" cols="50"/>
                            </td>
                        </tr>
                    </table>
                </div>
                <br/>
            </div>
            <br/>
        </fieldset>
        <fieldset class="aras1">
            <legend>Maklumat Pengadu</legend>
            <div class="subtitle" id="main">
                <div class="content" align="center">
                    <%--${actionBean.pihak.idPihak}--%>
                    <display:table style="width:50%" class="tablecloth" name="${actionBean.hakmilikPermohonan.hakmilik.senaraiPihakBerkepentingan}" cellpadding="1" cellspacing="1" requestURI="/pengambilan/kemasukanaduan1" id="line">
                        <%-- <display:column title="Pilih">
                             <s:radio name="radio_" id="${line.pihak.idPihak}" value="${line.pihak.idPihak}" style="width:15px"/>
                             <s:radio name="radio_" id="${line.pihak.idPihak}" value="${line.pihak.idPihak}" style="width:15px" onclick="selectRadio('${line.pihak.idPihak}')"/>
                             <s:param name="idPihak" value="${line.pihak.idPihak}"/>
                         </display:column>--%>
                        <display:column title="Pilih">
                            <s:radio name="selectedPihak2" value="${line.pihak.idPihak}"/>
                        </display:column>
                        <display:column title="No. IC" property="pihak.noPengenalan" style="vertical-align:top;"/>
                        <display:column title="Nama " property="pihak.nama" style="text-transform:uppercase;vertical-align:top;" />
                      <%--  <display:column title="Wakil?">
                            <s:radio name="selectedWakil" value="Ya" onclick="showWakil();"/>Ada
                            <s:radio name="selectedWakil" value="No" onclick="showWakil();"/>Tiada

                        </display:column>--%>
                    </display:table>
                </div>
                <br/>
                <%-- <div class="subtitle" id="main" id="hiddenWakil">
                        class="content"
                        Maklumat Wakil(Jika Ada)
                        <div align="center">
                        <table class="tablecloth" border="0" width="75%">
                            <tr>
                                <td><font color="red" size="4">*</font>No Pengenalan :</td>
                                <td>
                                    onchange="clearNoPengenalan();"
                                    <s:select name="permohonanPihak.jenisPengenalan.kod" id="permohonanPihak.jenisPengenalan.kod" value="${actionBean.pihak.jenisPengenalan.kod}">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiJenisPengenalanPelupusan}" label="nama" value="kod"/>
                                    </s:select> :
                                  <s:hidden name="permohonanPihak.jenisPengenalan.kod" id="permohonanPihak.jenisPengenalan.kod" value="permohonanPihak.jenisPengenalan.kod"/>
                                    <s:text name="permohonanPihak.noPengenalan" id="permohonanPihak.noPengenalan" value="${actionBean.permohonanPihak.noPengenalan}" size="20" onkeyup="dodacheck(this.value);" onblur="doUpperCase(this.id)"/>
                                    <font title="No KP Baru: 780104069871, No KP Lama: A2977884, No Syarikat: 115793-P, No Pertubuhan: 432483-U"><em>[780104069871]</em></font>
                                    <input type=button name="searchPenyerah" value="Cari" class="btn" id="carianPihak"
                                    onclick="javascript:populatePenyerah(this);" />
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Nama :</td>
                                <td>
                                    ${actionBean.pihak.nama}
                                    <s:text name="permohonanPihak.nama" id="permohonanPihak.nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Alamat :</td>
                                <td>
                                    ${actionBean.pihak.alamat1}
                                    <s:text name="permohonanPihak.alamat.alamat1" id="permohonanPihak.alamat.alamat1" size="40" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    ${actionBean.pihak.alamat2}
                                    <s:text name="permohonanPihak.alamat.alamat2" id="permohonanPihak.alamat.alamat2" size="40" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    ${actionBean.pihak.alamat3}
                                    <s:text name="permohonanPihak.alamat.alamat3" id="permohonanPihak.alamat.alamat3" size="40" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    ${actionBean.pihak.alamat4}
                                    <s:text name="permohonanPihak.alamat.alamat4" id="permohonanPihak.alamat.alamat4" size="40" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Poskod :</td>
                                <td>
                                    ${actionBean.pihak.poskod}
                                    <s:text name="permohonanPihak.alamat.poskod" size="10" id="permohonanPihak.alamat.poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Negeri :</td>
                                <td>
                                    ${actionBean.pihak.negeri.nama}
                                    <s:text name="permohonanPihak.negeri.nama" value="${actionBean.permohonanPihak.negeri.kod}" size="40" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                                    <s:select name="kodNegeriAlamat" value="${actionBean.permohonanPihak.alamat.negeri.kod}" id="negeri">
                                        <s:option value="0">Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>No Telefon :</td>
                                <td>
                                    ${actionBean.pihak.noTelefon1}
                                    <s:text name="permohonanPihak.noTelefon1" id="permohonanPihak.noTelefon1" size="14" onkeyup="validateNumber(this,this.value);"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Emel :</td>
                                <td>
                                   <s:text name="permohonanPihak.email" id="permohonanPihak.email" value="${actionBean.pihak.email}"size="40" class="normal_text"/></td>
                            </tr>
                             <tr>
                                <td><font color="red" size="4">*</font>Hubungan :</td>
                                <td>
                                   <s:text name="permohonanPihak.kaitan" id="permohonanPihak.kaitan" size="40" class="normal_text"/></td>
                            </tr>
                        </table>
                        </div>
                 </div>--%>
                <div class="content" align="center">
                    <table class="tablecloth" border="0" align="center">
                        <tr>
                            <td align="center" colspan="2">
                                <%--<s:button name="saveMohon" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>
                                <%--<s:button name="saveMohon" id="simpan" value="Simpan" class="btn" onclick="savePenjaga(this.name, this.form);"/>--%>
                                <c:if test="${actionBean.permohonan.idPermohonan eq null}">
                                <s:submit name="saveMohon" value="Seterusnya" class="btn" id="next"/>
                                </c:if>
                                <c:if test="${actionBean.permohonan.idPermohonan ne null}">
                                <s:submit name="saveMohon2" value="Seterusnya" class="btn" id="next"/>
                                </c:if>
                            </td>
                            <td><s:submit name="kembali" value="Kembali" class="btn"/></td>
                        </tr>
                    </table>
                </div>
            </div>
            <br/>
        </fieldset>
    </s:form>
</div>