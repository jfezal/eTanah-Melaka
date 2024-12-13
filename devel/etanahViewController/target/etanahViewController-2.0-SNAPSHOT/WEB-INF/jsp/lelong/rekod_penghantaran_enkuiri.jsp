<%-- 
    Document   : rekod_penghantaran_enkuiri
    Created on : Oct 18, 2010, 3:59:44 PM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<script type="text/javascript">
    function show_calendar(){
        window.open("${pageContext.request.contextPath}/lelong/keputusan_perintah?showFormC", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=12000,height=1200");
    }

    function validation() {
        if($("#datepicker1").val() == ""){
            alert('Sila masukkan " Tarikh Enkuiri " terlebih dahulu.');
            $("#datepicker1").focus();
            return false;
        }
        if($("#hari").val() == ""){
            alert('Sila masukkan " Hari " terlebih dahulu.');
            $("#hari").focus();
            return false;
        }
        if($("#tempat").val() == ""){
            alert('Sila masukkan " Tempat " terlebih dahulu.');
            $("#tempat").focus();
            return false;
        }

        return true;
    }

    function convertDay(value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var date = new Date(fulldate);
        var date1 = date.getDay();
        var hari = "";
        switch(date1){
            case 0:  hari = "Ahad";
                break;
            case 1:  hari = "Isnin";
                break;
            case 2:  hari = "Selasa";
                break;
            case 3:  hari = "Rabu";
                break;
            case 4:  hari = "Khamis";
                break;
            case 5:  hari = "Jumaat";
                break;
            case 6:  hari = "Sabtu";
                break;
            default :hari = "salah";
                break;
        }
        $('#hari').val(hari);
    }

</script>

<s:form beanclass="etanah.view.stripes.lelong.KeputusanRekodPenghantaranActionBean">
    <s:messages/>
    <s:errors/>
    <c:if test="${error}">
        <div class="subtitle" id="lain2">
            <fieldset class="aras1">
                <legend>
                    Maklumat Siasatan
                </legend>
                <div class="content">
                    <p>
                        <label><font color="red">*</font>Tarikh :</label>
                        <s:text name="tarikhEnkuiri" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" onchange="convertDay(this.value);"/>&nbsp;
                        <a href="javascript:show_calendar();">
                            <img src="${pageContext.request.contextPath}/pub/images/calendar.gif" id="" width=15 height=15 /></a>
                        <font color="red" size="1">(cth : hh / bb / tttt)</font>
                        &nbsp;
                    </p>
                    <p>
                        <label><font color="red">*</font>Masa :</label>
                        <s:select name="jam1" style="width:56px">
                            <s:option value="00">Jam</s:option>
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
                        <s:select name="minit1" style="width:56px">
                            <s:option value="00">Minit</s:option>
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
                        <s:select name="ampm1" style="width:80px">
                            <s:option value="0">Pilih</s:option>
                            <s:option value="AM">Pagi</s:option>
                            <s:option value="PM">Petang</s:option>
                        </s:select>

                    <p>
                        <label><font color="red">*</font> Hari :</label>
                        <s:text id="hari" name="enkuiri.tarikhEnkuiri" disabled="true" formatPattern="EEEE" />
                    </p>

                    <p>
                        <label><font color="red">*</font> Tempat :</label>
                        <s:textarea id="tempat" name="tempat" cols="50" rows="5"/>
                    </p>
                    <p>
                        <label>Perkara :</label>
                        <s:textarea id="perkara" name="perkara" cols="50" rows="5" />
                    </p>
                </div>
            </fieldset>
        </div>
        <div class="content" align="right">
            <p>
                <s:button name="simpanEnkuiri" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                <%--<s:reset name="" value="Isi Semula" class="btn"/>&nbsp;--%>
                <%--<s:button name="Simpan" value="Hantar" class="btn" />--%>
            </p>

        </div>
        <br>
        <fieldset class="aras1">
            <legend>
                Sejarah Siasatan
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiEnkuiri}" id="line">
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="Tarikh"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhEnkuiri}"/></display:column>
                    <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhEnkuiri}"/></display:column>
                    <%--<display:column title="Tarikh Enkuiri"><fmt:formatDate pattern="dd/MM/yyyy hh:mm aaa" value="${line.tarikhEnkuiri}"/></display:column>--%>
                    <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                    <display:column property="perkara" title="Perkara" class="nama${line_rowNum}"/>
                    <display:column property="status.nama" title="Status" class="nama${line_rowNum}"/>
                    <display:column property="ulasanPegawai" title="Ulasan" class="nama${line_rowNum}"/>
                </display:table>
            </div>
        </fieldset>
    </c:if>
</s:form>
