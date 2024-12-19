<%-- 
    Document   : laporan_enkuiri
    Created on : June 22, 2010, 6:00:05 PM
    Author     : nurshahida.radzi
    Modified by : ctzainal
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
  

    function show_calendar (){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_enkuiri?showForm4", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=1200,scrollbars=1");
    }
    function validation() {
        if($("#datepicker").val() == ""){
            alert('Sila masukkan " Tarikh Enkuiri " terlebih dahulu.');
            $("#datepicker").focus();
            return false;
        }

        if($("#jam").val() == ""){
            alert('Sila pilih " Jam " terlebih dahulu.');
            $("#jam").focus();
            return false;
        }

        if($("#minit").val() == ""){
            alert('Sila Pilih " Minit " terlebih dahulu.');
            $("#minit").focus();
            return false;
        }

        if($("#ampm").val() == ""){
            alert('Sila Pilih " AM/PM "');
            $("#ampm").focus();
            return false;
        }
        
        if($("#tempat").val() == ""){
            alert('Sila masukkan " Tempat " terlebih dahulu.');
            $("#tempat").focus();
            return false;
        }
        
    <%--if($("#catatan").val() == ""){
        alert('Sila masukkan " Perkara " terlebih dahulu.');
        $("#catatan").focus();
        return false;
    }--%>
        
            return true;
        }

        function convertDay(value){
            var vsplit = value.split('/');
            var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
            var date = new Date(fulldate);
            var date1 = date.getDay();
            var today =  new Date();
            if (date < today){
                alert("Sila masukkan tarikh esok atau tarikh hari seterusnya.");
                $("#datepicker").val("");
                $('#hari').val("");
            }else{
                var hari = "";
                switch(date1){
                    case 0:  hari = "AHAD";
                        break;
                    case 1:  hari = "ISNIN";
                        break;
                    case 2:  hari = "SELASA";
                        break;
                    case 3:  hari = "RABU";
                        break;
                    case 4:  hari = "KHAMIS";
                        break;
                    case 5:  hari = "JUMAAT";
                        break;
                    case 6:  hari = "SABTU";
                        break;
                    default :hari = "ERROR";
                        break;
                }
                $('#hari').val(hari);
            }
        }

        function test(f) {
            $(f).clearForm();
        }

        function showReport(){
            window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_enkuiri?janaNotis", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
        }

        function deleteEnkuiri(id){
            if(confirm('Adakah anda pasti untuk hapus?')) {
                var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_enkuiri?deleteEnkuiri&idEnkuiri='+id;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');}
        }
</script>

<s:form name="maklumat_laporan_enkuiri" beanclass="etanah.view.penguatkuasaan.MaklumatLaporanEnkuiriActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle displaytag" id="lain2">
        <c:if test="${edit}">
            <fieldset class="aras1">
                <legend>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne '127'}">
                        Maklumat Enkuiri
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '127'}">
                        <c:if test="${actionBean.permohonan.keputusan.kod eq 'DD'}">
                            Borang 7E
                        </c:if>
                    </c:if>
                </legend>
                <div class="content">
                    <p>
                        <label><font color="red">*</font>Tarikh :</label>
                        <s:text name="tarikhMula"  id="datepicker" onclick="show_calendar();"/>&nbsp;
                        <font color="red" size="1">cth : hh / bb / tttt</font>
                        &nbsp;
                    </p>
                    <p>
                        <label><font color="red">*</font>Masa :</label>
                        <s:select name="jam" id="jam" style="width:56px">
                            <s:option value="">Jam</s:option>
                            <s:option value="01">01</s:option>
                            <s:option value="02">02</s:option>
                            <s:option value="03">03</s:option>
                            <s:option value="04">04</s:option>
                            <s:option value="05">05</s:option>
                            <s:option value="06">06</s:option>
                            <s:option value="07">07</s:option>
                            <s:option value="08">08</s:option>
                            <s:option value="09">09</s:option>
                            <s:option value="10">10</s:option>
                            <s:option value="11">11</s:option>
                            <s:option value="12">12</s:option>
                        </s:select>:
                        <s:select name="minit" id="minit" style="width:56px">
                            <s:option value="">Minit</s:option>
                            <s:option value="00">00</s:option>
                            <s:option value="05">05</s:option>
                            <s:option value="10">10</s:option>
                            <s:option value="15">15</s:option>
                            <s:option value="20">20</s:option>
                            <s:option value="25">25</s:option>
                            <s:option value="30">30</s:option>
                            <s:option value="35">35</s:option>
                            <s:option value="40">40</s:option>
                            <s:option value="45">45</s:option>
                            <s:option value="50">50</s:option>
                            <s:option value="55">55</s:option>
                        </s:select>:

                        <s:select name="ampm" id="ampm" style="width:80px">
                            <s:option value="">Pilih</s:option>
                            <s:option value="AM">PAGI</s:option>
                            <s:option value="PM">PETANG</s:option>
                        </s:select>
                    <p>
                        <label><font color="red">*</font> Hari :</label>
                        <s:text id="hari" name="enkuiri.tarikhMula" disabled="true" formatPattern="EEEE" />
                    </p>
                    <p>
                        <label><font color="red">*</font>Tempat :</label>
                        <s:textarea name="tempat" id="tempat" cols="50" rows="5" class="normal_text" />
                    </p>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne '127'}">
                        <p>
                            <label><font color="red">*</font> Perkara :</label>
                            <s:textarea id="catatan" name="catatan" cols="50" rows="5" />
                        </p>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '127'}">
                        <p>
                            <label>Perihal Langgar Syarat :</label>
                            <s:textarea id="catatan" name="catatan" cols="50" rows="5" />
                        </p>
                    </c:if>

                    <p>
                        <label>Ditandatangan Oleh :</label>
                        <s:select name="diTandatanganOleh">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiPengguna}" label="nama" value="idPengguna" sort="nama" />
                        </s:select>
                    </p>
                    <p align="right">
                        <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                        <s:button name="simpanEnkuiri" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>

                    </p>
                </div>
            </fieldset>
            <br>
        </c:if>
        <fieldset class="aras1">
            <legend>
                Sejarah Enkuiri
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiEnkuiri}" pagesize="10" id="line" requestURI="/penguatkuasaan/maklumat_laporan_enkuiri">
                    <%--<display:column property="bil" title="Bil. Enkuiri" class="nama${line_rowNum}" sortName="bil"/>--%>
                    <display:column title="Tarikh dan Masa Enkuiri" sortable="true"><fmt:formatDate pattern="dd/MM/yyyy hh:mm aaa" value="${line.tarikhMula}"/></display:column>
                    <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                    <display:column property="kodJenisEnkuiri.kod" title="Jenis Enkuiri"/>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne '127'}">
                        <display:column property="catatan" title="Perkara" class="nama${line_rowNum}"/>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '127'}">
                        <display:column property="catatan" title="Perihal Langgar Syarat" class="nama${line_rowNum}"/>
                    </c:if>
                    <c:if test="${edit}">
                        <display:column title="Hapus">
                            <c:if test="${actionBean.kodJenisEnkuiri eq line.kodJenisEnkuiri.kod}">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="deleteEnkuiri('${line.idEnkuiri}');"/>
                                </div> 
                            </c:if>
                        </display:column>
                    </c:if>

                </display:table>
                <p>&nbsp;</p>
                <%--                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '127'}">
                                        <c:if test="${!edit7b && !edit}">
                                            <s:button class="btn" onclick="showReport();" name="" value="Jana Notis"/>
                                        </c:if>
                                    </c:if>--%>
            </div>
        </fieldset>
        <c:if test="${!edit}">
            <div class="subtitle">
                <fieldset class="aras1">
                    <c:if test="${not empty actionBean.enkuiri.idEnkuiri}">
                        <div class="content">
                            <legend>
                                Maklumat Enkuiri
                            </legend>
                            <p>
                                <label>Tarikh :</label>
                                ${actionBean.tarikhMula}&nbsp;
                            </p>
                            <p>
                                <label>Masa :</label>
                                ${actionBean.jam}:${actionBean.minit} ${actionBean.ampm} &nbsp;

                            <p>
                                <label>Hari :</label>
                                <fmt:formatDate pattern="EEEE" value="${actionBean.enkuiri.tarikhMula}"/>&nbsp;
                            </p>
                            <p>
                                <label>Tempat :</label>
                                ${actionBean.tempat} &nbsp;
                            </p>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne '127'}">
                                <p>
                                    <label>Perkara :</label>
                                    ${actionBean.catatan}&nbsp;
                                </p>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq '127'}">
                                <p>
                                    <label>Perihal Langgar Syarat :</label>
                                    ${actionBean.catatan}&nbsp;
                                </p>
                            </c:if>

                            <p>
                                <label>Ditandatangan Oleh :</label>
                                <s:select name="diTandatanganOleh" disabled="true">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${actionBean.senaraiPengguna}" label="nama" value="idPengguna" sort="nama" />
                                </s:select>
                            </p>

                        </div>

                        <br>

                        <legend>
                            Sejarah Enkuiri
                        </legend>
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.senaraiEnkuiri}" cellpadding="0" cellspacing="0" id="line1">
                                <display:column title="Bil">${line1_rowNum}</display:column>
                                <display:column title="Tarikh">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${line1.tarikhMula}"/>&nbsp;
                                </display:column>
                                <display:column title="Masa">
                                    <fmt:formatDate pattern="hh:mm aaa" value="${line1.tarikhMula}"/>&nbsp;
                                </display:column>
                                <display:column title="Hari"></display:column>
                                <display:column title="Tempat" property="tempat"></display:column>
                            </display:table>
                            <p>&nbsp;</p>
                        </div>
                    </fieldset>
                </div>

            </c:if>
        </c:if>
    </div>
</s:form>
