<%-- 
    Document   : maklumat_tetap_enkuiri
    Created on : Jan 14, 2010, 5:40:58 PM
    Author     : farah.shafilla
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type="text/javascript">


    function show_calendar(){
        window.open("${pageContext.request.contextPath}/lelong/maklumat_enkuiri?showForm3", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1200,height=1200,scrollbars=1");
    }

    function validation() {
        if($("#datepicker").val() == ""){
            alert('Sila masukkan " Tarikh Enkuiri "');
            $("#datepicker").focus();
            return false;
        }
        if($("#jam").val() == "00"){
            alert('Sila Pilih " Jam "');
            $("#jam").focus();
            return false;
        }
        
        if($("#minit").val() == "null"){
            alert('Sila Pilih " Minit "');
            $("#minit").focus();
            return false;
        }
        if($("#ampm").val() == "0"){
            alert('Sila Pilih " Pagi/Petang "');
            $("#ampm").focus();
            return false;
        }

        if($("#hari").val() == ""){
            alert('Sila Masukkan " Hari "');
            $("#hari").focus();
            return false;
        }
        if($("#tempat").val() == ""){
            alert('Sila Masukkan " Tempat "');
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

<s:form beanclass="etanah.view.stripes.lelong.MaklumatEnkuiriActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle" id="lain2">
        <fieldset class="aras1">
            <legend>
                Maklumat Siasatan
            </legend>
            <div class="content">
                <p>
                    <label><font color="red">*</font>Tarikh :</label>
                    <s:text name="tarikhEnkuiri" id="datepicker" onclick="show_calendar();"/>&nbsp;
                    <%--<a href="javascript:show_calendar();">
                        <img src="${pageContext.request.contextPath}/pub/images/calendar.gif" id="" width=15 height=15 /></a>--%>
                    <font color="red" size="1">(cth : hh / bb / tttt)</font>
                    &nbsp;
                </p>

                <p>
                    <label><font color="red">*</font>Masa :</label>
                    <s:select id="jam" name="jam" style="width:56px">
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
                    <s:select id="minit" name="minit" style="width:65px">
                        <s:option value="null">Minit</s:option>
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

                    <s:select id="ampm" name="ampm" style="width:80px">
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
                    <s:textarea id="tempat" name="enkuiri.tempat" cols="50" rows="5" />
                </p>
                <p>
                    <label> Perkara :</label>
                    <s:textarea id="perkara" name="enkuiri.perkara" cols="50" rows="5" />
                </p>
                <%--user demand -jasin(simulasi) 28/8/2014--%>
                <%--<p>
                    <label> Cara Serahan :</label>
                    <s:select name="kodCaraPenghantaran" id="kodPenghantaran" value="${actionBean.notiss.kodCaraPenghantaran.kod}">
                        <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod"/>
                    </s:select>
                </p>--%>
            </div>

            <div class="content" align="center">
                <p>
                    <c:if test="${actionBean.view eq false}">
                        <s:button name="simpanEnkuiri" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                    <c:if test="${actionBean.view eq true}">
                        <s:button name="simpanEnkuiri2" id="save" value="Tukar Tarikh" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                </p>

            </div>
            <br>
        </fieldset>
    </div>
    <br>
    <c:if test="${fn:length(actionBean.senaraiEnkuiri3)>0}">
        <fieldset class="aras1">
            <legend>
                Sejarah Siasatan
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiEnkuiri3}" id="line">
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="Tarikh"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhEnkuiri}"/></display:column>
                    <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhEnkuiri}"/></display:column>
                    <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                    <display:column property="perkara" title="Perkara" class="nama${line_rowNum}"/>
                    <display:column property="status.nama" style="text-transform: uppercase;" title="Status" class="nama${line_rowNum}"/>
                    <display:column property="ulasanPegawai" title="Ulasan" class="nama${line_rowNum}"/>
                </display:table>
            </div>
        </fieldset>
    </c:if>

</s:form>


