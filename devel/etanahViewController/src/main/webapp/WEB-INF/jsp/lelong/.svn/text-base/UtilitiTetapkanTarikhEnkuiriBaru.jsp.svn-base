<%-- 
    Document   : UtilitiTetapkanTarikhEnkuiriBaru
    Created on : Sep 27, 2010, 10:00:50 AM
    Author     : mazurahayati.yusop
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<%--<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>--%>

<script type="text/javascript">

    function showReport(val){
        var url = '${pageContext.request.contextPath}/dokumen/view/' + val;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function show_calendar(){
        window.open("${pageContext.request.contextPath}/lelong/utiliti_maklumat_enkuiri?showForm3", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1200,height=1200,scrollbars=1");
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

<s:form name="UtilitiTetapkanTarikhEnkuiriBaru" beanclass="etanah.view.stripes.lelong.UtilitiTetapkanTarikhEnkuiriBaruActionBean">
    <s:messages/>
    <s:errors/>
    <%--<s:hidden name="enkuiri.idEnkuiri"/>--%>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian
            </legend>
            <p>
                <label> ID Permohonan :</label>
                <s:text name="idPermohonan" maxlength="20" size="31" onblur="this.value=this.value.toUpperCase();"/>
            </p>

            <p align="center">
                <s:submit name="find" value="Cari" class="btn" />
            </p>
        </fieldset>
    </div>

    <c:if test="${actionBean.flag eq true}">

        <div class="subtitle" id="">
            <fieldset class="aras1">
                <legend>
                    Maklumat Enkuiri
                </legend>
                <div class="content">
                    <p>
                        <label><font color="red">*</font>Tarikh :</label>
                        <s:text name="tarikhEnkuiri" id="datepicker" onclick="show_calendar();" formatPattern="dd/MM/yyyy"/>&nbsp;
                        <a href="javascript:show_calendar();">
                            <img src="${pageContext.request.contextPath}/pub/images/calendar.gif" id="" width=15 height=15 /></a>
                        <font color="red" size="1">(cth : hh / bb / tttt)</font>
                        &nbsp;
                    </p>

                    <p>
                        <label><font color="red">*</font>Masa :</label>
                        <s:select name="jam" style="width:56px">
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
                        <s:select name="minit" style="width:65px">
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

                        <s:select name="ampm" style="width:80px">
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
                        <s:textarea id="tempat" name="tempat" cols="50" rows="5" onblur="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Perkara :</label>
                        <s:textarea id="perkara" name="perkara" cols="50" rows="5" onblur="this.value=this.value.toUpperCase();" />
                    </p>
                </div>
            </fieldset>
        </div>
        <div class="content" align="right">
            <p align="center">
                <s:submit name="simpanEnkuiri" id="save" value="Simpan" class="btn"/>
                <%--<s:reset name="" value="Isi Semula" class="btn"/>&nbsp;--%>
                <%--<s:button name="Simpan" value="Hantar" class="btn" />--%>
            </p>

        </div>
        <c:if test="${fn:length(actionBean.senaraiEnkuiri)>0}">
            <br>
            <fieldset class="aras1">
                <legend>
                    Sejarah Enkuiri
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiEnkuiri}" pagesize="3" id="line">
                        <%--<display:column property="bil" title="Bil. Enkuiri" class="nama${line_rowNum}" sortName="bil"/>--%>
                        <display:column title="Tarikh"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhEnkuiri}"/></display:column>
                        <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhEnkuiri}"/></display:column>
                        <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                        <display:column property="perkara" title="Perkara" class="nama${line_rowNum}"/>
                        <display:column style="text-transform:uppercase;" property="status.nama" title="Status" class="nama${line_rowNum}"/>
                    </display:table>
                </div>
            </fieldset>
        </c:if>

        <p align="center">
            <s:button name="" onclick="showReport('${idDok}');" value=" Notis Siasatan" class="longbtn"/>
        </p>
    </c:if>
    <br>


</s:form>

