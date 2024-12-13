<%--
    Document   : maklumat_pelelongan.jsp
    Created on : Oct 07, 2013 10:42:28 AM
    Author     : Hayyan
    TXN        : PJTK N9
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<%-- carian hakmilik start--%>

<%--<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />--%>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>--%>

<script type="text/javascript">
    function validateForm1()
    {   //if($('#tujuanRezab').val() == "")
        //{   alert("Sila masukkan Tujuan Pelelongan.");
        //    return false;
        //}
        if($('#bayaranBorang').val() == "")
        {   alert("Sila masukkan Bayaran Borang.");
            return false;
        }
        if($('#datepicker1').val() == "")
        {   alert("Sila masukkan Tarikh Mula Beli Borang.");
            return false;
        }
        if($('#datepicker2').val() == "")
        {   alert("Sila masukkan Tarikh Akhir Beli Borang.");
            return false;
        }
        if($('#datepicker3').val() == "")
        {   alert("Sila masukkan Tarikh Lawatan Tapak.");
            return false;
        }
        if($('#jam').val() == "")
        {   alert("Sila masukkan Waktu Lawatan Tapak.");
            return false;
        }
        if($('#minit').val() == "")
        {   alert("Sila masukkan Waktu Lawatan Tapak.");
            return false;
        }
        if($('#ampm').val() == "")
        {   alert("Sila masukkan Waktu Lawatan Tapak.");
            return false;
        }
        if($('#datepicker4').val() == "")
        {   alert("Sila masukkan Tarikh Tamat Daftar.");
            return false;
        }
        if($('#jam1').val() == "")
        {   alert("Sila masukkan Waktu Tamat Daftar.");
            return false;
        }
        if($('#minit1').val() == "")
        {   alert("Sila masukkan Waktu Tamat Daftar.");
            return false;
        }
        if($('#ampm1').val() == "")
        {   alert("Sila masukkan Waktu Tamat Daftar.");
            return false;
        }
        if($('#datepicker5').val() == "")
        {   alert("Sila masukkan Tarikh Lelongan.");
            return false;
        }
        if($('#jam2').val() == "")
        {   alert("Sila masukkan Waktu Lelongan.");
            return false;
        }
        if($('#minit2').val() == "")
        {   alert("Sila masukkan Waktu Lelongan.");
            return false;
        }
        if($('#ampm2').val() == "")
        {   alert("Sila masukkan Waktu Lelongan.");
            return false;
        }
        if($('#tempatLelongan').val() == "")
        {   alert("Sila masukkan Tempat Lelongan.");
            return false;
        }
        return true;
    }
    function validateForm2()
    {   if($('#hargaRizab').val() == "")
        {   alert("Sila masukkan Harga Rizab.");
            return false;
        }
        if($('#deposit').val() == "")
        {   alert("Sila masukkan Deposit.");
            return false;
        }
        return true;
    }
    function validateForm3()
    {   if($('#noWarta').val() == "")
        {   alert("Sila masukkan Nombor Warta.");
            return false;
        }
        if($('#datepicker6').val() == "")
        {   alert("Sila masukkan Tarikh Warta.");
            return false;
        }
        return true;
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form id="form" name="form" beanclass="etanah.view.stripes.pelupusan.MaklumatPelelonganActionBean">
    <s:messages/>
    <s:errors/>
    <c:if test="${!view and !viewplus and actionbean.getStageId ne '14KmsknNoWarta'}">    
    <div class="subtitle" >
        <fieldset class="aras1">         
            <legend>Maklumat Pelelongan</legend>
                <table>
<%--                    <tr>
                        <td>&nbsp;</td>
                        <td valign="top"><label><em>*</em>Tujuan Pelelongan :</label></td>
                        <td><s:textarea id="tujuanRezab" name="tujuanRezab" class="normal_text" cols="45" rows=""></s:textarea></td>
                    </tr>--%><tr>
                        <td>&nbsp;</td>
                        <td><label><em>*</em>Bayaran Borang (RM) :</label></td>
                        <td><s:text id="bayaranBorang" name="bayaranBorang" size="20" class="number" formatPattern="0.00" maxlength="3"></s:text></td>
                    </tr><tr>
                        <td>&nbsp;</td>
                        <td><label><em>*</em>Tarikh Mula Beli Borang :</label></td>
                        <td><s:text id="datepicker1" name="tarikhMulaBorang" class="datepicker" formatPattern="dd/MM/yyyy"></s:text></td>
                    </tr><tr>
                        <td>&nbsp;</td>
                        <td><label><em>*</em>Tarikh Akhir Beli Borang :</label></td>
                        <td><s:text id="datepicker2" name="tarikhAkhirBorang" class="datepicker" formatPattern="dd/MM/yyyy"></s:text></td>
                    </tr><tr>
                        <td>&nbsp;</td>
                        <td><label><em>*</em>Tarikh Lawatan Tapak :</label></td>
                        <td><s:text id="datepicker3" name="tarikhLawatanTapak" class="datepicker" formatPattern="dd/MM/yyyy"></s:text></td>
                    </tr><tr>
                        <td>&nbsp;</td>
                        <td><label><em>*</em>Waktu Lawatan Tapak :</label></td>
                        <td><s:select id="jam" name="jam" style="width:56px">
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
                        </s:select>
                        <s:select id="minit" name="minit" style="width:66px">
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
                        </s:select>                        
                        <s:select name="ampm" id="ampm" style="width:80px">
                                <s:option value="">Pilih</s:option>
                                <s:option value="AM">PAGI</s:option>
                                <s:option value="PM">PETANG</s:option>
                        </s:select></td>
                    </tr><tr>
                        <td>&nbsp;</td>
                        <td><label><em>*</em>Tarikh Tamat Daftar :</label></td>
                        <td><s:text id="datepicker4" name="tarikhTamatPendaftaran" class="datepicker" formatPattern="dd/MM/yyyy"></s:text></td>
                    </tr><tr>
                        <td>&nbsp;</td>
                        <td><label><em>*</em>Waktu Tamat Daftar :</label></td>
                        <td><s:select id="jam1" name="jam1" style="width:56px">
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
                        </s:select>
                        <s:select id="minit1" name="minit1" style="width:66px">
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
                        </s:select>
                        <s:select name="ampm1" id="ampm1" style="width:80px">
                                <s:option value="">Pilih</s:option>
                                <s:option value="AM">PAGI</s:option>
                                <s:option value="PM">PETANG</s:option>
                        </s:select></td>
                    </tr><tr>
                        <td>&nbsp;</td>
                        <td><label><em>*</em>Tarikh Lelongan :</label></td>
                        <td><s:text id="datepicker5" name="tarikhLelongan" class="datepicker" formatPattern="dd/MM/yyyy"></s:text></td>
                    </tr><tr>
                        <td>&nbsp;</td>
                        <td><label><em>*</em>Waktu Lelongan :</label></td>
                        <td><s:select id="jam2" name="jam2" style="width:56px">
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
                        </s:select>
                        <s:select id="minit2" name="minit2" style="width:66px">
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
                        </s:select>
                        <s:select name="ampm2" id="ampm2" style="width:80px">
                                <s:option value="">Pilih</s:option>
                                <s:option value="AM">PAGI</s:option>
                                <s:option value="PM">PETANG</s:option>
                        </s:select></td>
                    </tr><tr>
                        <td>&nbsp;</td>
                        <td valign="top"><label><em>*</em>Tempat Lelongan :</label></td>
                        <td><s:textarea id="tempatLelongan" name="tempatLelongan" class="normal_text" cols="45" rows=""></s:textarea></td>
                    </tr><tr>
                        <td>&nbsp;</td><td>&nbsp;</td>
                        <td>
                            <s:reset name="reset" value="Isi Semula" class="btn"/>
                            <s:button name="saveData" id="save" value="Simpan" class="btn" onclick="if(validateForm1())doSubmit(this.form,this.name,'page_div');"/> 
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
        </fieldset>
    </div>
    </c:if>
    <c:if test="${view}">
    <div class="subtitle" >
        <fieldset class="aras1">
            <legend>Maklumat Pelelongan</legend>
                <table cellpadding="5" cellspacing=5">
                    <%--<tr>
                        <td>&nbsp;</td>                        
                        <td><label>Tujuan Pelelongan :</label></td>
                        <td>${actionBean.tujuanRezab}</td>                        
                    </tr>--%>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Bayaran Borang (RM) :</label></td>
                        <td><s:format formatPattern="#,##0.00" value="${actionBean.bayaranBorang}"/></td>                     
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Tarikh Mula Beli Borang :</label></td>
                        <td><s:format formatPattern="dd/MM/yyyy" value="${actionBean.tarikhMulaBorang}"/></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Tarikh Akhir Beli Borang :</label></td>
                        <td><s:format formatPattern="dd/MM/yyyy" value="${actionBean.tarikhAkhirBorang}"/></td>                     
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Tarikh Lawatan Tapak :</label></td>
                        <td>${actionBean.tarikhLawatanTapak}</td>                     
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Waktu Lawatan Tapak :</label></td>
                        <td><s:format value="${actionBean.waktuLawatanTapak}"/></td>
                        <%--<td><fmt:formatDate pattern="hh:mm" value="${line.tarikhLawatanTapak}"/></td>
                        <td><fmt:formatDate pattern="aaa" value="${line.tarikhLawatanTapak}" var="time"/>
                            <c:if test="${time eq 'AM'}">Pagi</c:if>
                            <c:if test="${time eq 'PM'}">Petang</c:if>
                        </td>--%>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Tarikh Tamat Daftar :</label></td>
                        <td>${actionBean.tarikhTamatPendaftaran}</td>                     
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Waktu Tamat Daftar :</label></td>
                        <td>${actionBean.waktuTamatPendaftaran}</td>                     
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Tarikh Lelongan :</label></td>
                        <td>${actionBean.tarikhLelongan}</td>                     
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Waktu Lelongan :</label></td>
                        <td>${actionBean.waktuLelongan}</td>                     
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Tempat Lelongan :</label></td>
                        <td>${actionBean.tempatLelongan}</td>
                    </tr>
                    <c:if test="${actionBean.stageId eq 'laporan_tanah'}">
                    <tr>
                        <td>&nbsp;</td>
                        <td><label><em>*</em>Harga Rizab (RM) :</label></td>
                        <td><s:text id="hargaRizab" name="hargaRizab" size="20" class="number" formatPattern="0.00" maxlength="7"></s:text></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label><em>*</em>Deposit (RM) :</label></td>
                        <td><s:text id="deposit" name="deposit" size="20" class="number" formatPattern="0.00" maxlength="7"></s:text></td>
                    </tr>                    
                    <tr>
                        <td>&nbsp;</td><td>&nbsp;</td>
                        <td>
                            <s:reset name="reset" value="Isi Semula" class="btn"/>
                            <s:button name="saveHarga" id="save" value="Simpan" class="btn" onclick="if(validateForm2())doSubmit(this.form,this.name,'page_div')"/> 
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                </c:if>
                </table>
        </fieldset>
    </div>
    </c:if>
    <c:if test="${viewplus}">
    <div class="subtitle" >
        <fieldset class="aras1">
            <legend>Maklumat Pelelongan</legend>
                <table cellpadding="5" cellspacing=5">
                    <tr>
                        <td>&nbsp;</td>                        
                        <td><label>Tujuan Pelelongan :</label></td>
                        <td>${actionBean.tujuanRezab}</td>                     
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Bayaran Borang (RM) :</label></td>
                        <td><s:format formatPattern="#,##0.00" value="${actionBean.bayaranBorang}"/></td>                     
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Tarikh Mula Beli Borang :</label></td>
                        <td><s:format formatPattern="dd/MM/yyyy" value="${actionBean.tarikhMulaBorang}"/></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Tarikh Akhir Beli Borang :</label></td>
                        <td><s:format formatPattern="dd/MM/yyyy" value="${actionBean.tarikhAkhirBorang}"/></td>                     
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Tarikh Lawatan Tapak :</label></td>
                        <td>${actionBean.tarikhLawatanTapak}</td>                     
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Waktu Lawatan Tapak :</label></td>
                        <td><s:format value="${actionBean.waktuLawatanTapak}"/></td>                     
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Tarikh Tamat Daftar :</label></td>
                        <td>${actionBean.tarikhTamatPendaftaran}</td>                     
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Waktu Tamat Daftar :</label></td>
                        <td>${actionBean.waktuTamatPendaftaran}</td>                     
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Tarikh Lelongan :</label></td>
                        <td>${actionBean.tarikhLelongan}</td>                     
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Waktu Lelongan :</label></td>
                        <td>${actionBean.waktuLelongan}</td>                     
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Tempat Lelongan :</label></td>
                        <td>${actionBean.tempatLelongan}</td>                     
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Harga Rizab (RM) :</label></td>
                        <td><s:format formatPattern="#,##0.00" value="${actionBean.hargaRizab}"/></td>                     
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Harga Deposit (RM) :</label></td>
                        <td><s:format formatPattern="#,##0.00" value="${actionBean.deposit}"/></td>                     
                    </tr>                    
                </table>
        </fieldset>
    </div>
    </c:if>
    <c:if test="${actionBean.stageId eq '14KmsknNoWarta'}">
    <div class="subtitle" >
        <fieldset class="aras1">
            <legend>Maklumat Warta</legend>
                <table>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label><em>*</em>Nombor Warta :</label></td>
                        <td><s:text id="noWarta" name="noWarta" size="20"></s:text></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label><em>*</em>Tarikh Warta :</label></td>
                        <td><s:text id="datepicker6" name="tarikhWarta" class="datepicker" formatPattern="dd/MM/yyyy"></s:text></td>
                    </tr>                    
                    <tr>
                        <td>&nbsp;</td><td>&nbsp;</td>
                        <td>
                            <s:reset name="reset" value="Isi Semula" class="btn"/>
                            <s:button name="saveWarta" id="save" value="Simpan" class="btn" onclick="if(validateForm3())doSubmit(this.form,this.name,'page_div')"/> 
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
        </fieldset>
    </div>
    </c:if>
    <c:if test="${actionBean.stageId eq '15ImbsnWarta'}">
    <div class="subtitle" >
        <fieldset class="aras1">
            <legend>Maklumat Warta</legend>
                <table cellpadding="5" cellspacing="5">
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Nombor Warta :</label></td>
                        <td>${actionBean.noWarta}</td>                     
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Tarikh Warta :</label></td>
                        <td><s:format formatPattern="dd/MM/yyyy" value="${actionBean.tarikhWarta}"/></td>
                    </tr>                    
                </table>
        </fieldset>
    </div>
    </c:if>
</s:form>
