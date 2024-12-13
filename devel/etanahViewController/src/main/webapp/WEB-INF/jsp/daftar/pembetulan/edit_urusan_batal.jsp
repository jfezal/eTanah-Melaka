<%--
    Document   : edit_urusan
    Created on : May 10, 2010, 4:08:06 PM
    Author     : wan.fairul
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="/etanah/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/date.format.js"></script>
<style type="text/css">
    td.s{
        font-weight:normal;
        color:black;
        text-align: left;

    }
    input, select{width:95%}
    td{
        font-size: 100% !important;
    }
    .arrow { background:transparent url(${pageContext.request.contextPath}/pub/images/arrows.png) no-repeat scroll 0px -16px; width:100%; height:16px; display:block;}
    .up { background-position:0px 0px;}
</style>
<script type="text/javascript">
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        var val = $('#pjanjianAmaun').val();
        if (val != '') {
            convert(val, 'pjanjianAmaun');
            val = $('#pjanjianAmaun').val();
            doCalculateDutiStem('pjanjianDutiSetam', val);
        }
    });


    function save(event, f, idUrusan)
    {
        var q = $(f).formSerialize();
        var url = f.action + '?' + event + '&idUrusan=' + idUrusan;
        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);
                    self.close();
                }, 'html');

    }


    function doCalculateDutiStem(id, amt) {
        var _q = amt.indexOf(",");
        if (_q > 0) {
            amt = amt.replace(/,/g, "");
        }
        //alert(amt);
        var tmp = parseFloat(amt);
        if (tmp > 100000) {
            tmp = tmp * 0.02;
        } else {
            tmp = tmp * 0.01;
        }
        //convert(tmp, id);
        //$('#'+id).val(tmp);
        var amaun = cf(tmp);
        amaun = cf2(amaun);
        $('#' + id).val(amaun);
    }

    function traslateToCurrency(val, id) {
        val = cf(val);
        alert(val);
        val = cf2(val);
        alert(val);
        $('#' + id).val(val);
    }

    function cf(amount)
    {
        var t = amount;
        //alert("q" + t.indexOf(","));
        //var q = t.indexOf(",");
        //if(q > 0){
        //    amount = t.replace (/,/g, "");
        //}
        var i = parseFloat(amount);
        if (isNaN(i)) {
            i = 0.00;
        }
        var minus = '';
        if (i < 0) {
            minus = '-';
        }
        i = Math.abs(i);
        i = parseInt((i + .005) * 100);
        i = i / 100;
        s = new String(i);
        if (s.indexOf('.') < 0) {
            s += '.00';
        }
        if (s.indexOf('.') == (s.length - 2)) {
            s += '0';
        }
        s = minus + s;
        return s;
    }

    function cf2(amount)
    {
        var delimiter = ","; // replace comma if desired
        var a = amount.split('.', 2)
        var d = a[1];
        var i = parseInt(a[0]);
        if (isNaN(i)) {
            return '';
        }
        var minus = '';
        if (i < 0) {
            minus = '-';
        }
        i = Math.abs(i);
        var n = new String(i);
        var a = [];
        while (n.length > 3)
        {
            var nn = n.substr(n.length - 3);
            a.unshift(nn);
            n = n.substr(0, n.length - 3);
        }
        if (n.length > 0) {
            a.unshift(n);
        }
        n = a.join(delimiter);
        if (d.length < 1) {
            amount = n;
        } else {
            amount = n + '.' + d;
        }
        amount = minus + amount;
        return amount;
    }

    function doCalcEndDate(id) {
        var hari = parseInt($('.hari').val(), 10);
        var bln = parseInt($('.bulan').val(), 10);
        var thn = parseInt($('.tahun').val(), 10);
        var kodUrusan = $('#kodUrusan').val();

        if ($('#' + id).val() == '') {
            return;
        }

        if (isNaN(hari) && isNaN(bln) && isNaN(thn) && kodUrusan != 'PJLT') {
            alert('Sila Masukan Tempoh.');
            $('#' + id).val('');
            return;
        }
        if (hari == '0' && bln == '0' && thn == '0')
            return;
        var startDate = $('#' + id).val();
        //manual process :: should find conclusion on this
        var y = parseInt(startDate.substring(6, 10), 10);
        var m = parseInt(startDate.substring(3, 5), 10);
        var d = parseInt(startDate.substring(0, 2), 10);

        if (!isNaN(hari))
        {
            d = d + hari;
            if (d == 0) {
                m = m - 1;
            }
        }

        if (!isNaN(bln)) {
            m = m + bln;
            if (m > 12) {
                y = y + 1;
                m = m - 12;
                if (m == 2) {
                    var isleap = (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0));
                    if (d >= 30) {
                        if (isleap) {
                            d = 28;
                        } else {
                            d = 27;
                        }
                    } else if (d == 0) {
                        if (isleap) {
                            d = 29;
                        } else {
                            d = 28;
                        }
                    }
                }
            }
        }

        if (!isNaN(thn))
        {
            y = y + thn;
            if (d == 0) {
                m = m - 1;
            }
        }

        if (d == 0 && m == 0) {
            y = y - 1;
        }
        //y = y + thn;
        var endDate = new Date();
        var s = 1;
        endDate.setDate(d);
        endDate.setMonth(m - 1);
        endDate.setFullYear(y);
        endDate.setDate(endDate.getDate() - s);

        $('#tarikhTamat').val(endDate.format("dd/mm/yyyy"));
        $('#tarikhDaftarBaru').val(endDate.format("dd/mm/yyyy"));
    }


    function calculateDates(id) {
        //        var duration = parseInt(document.getElementById('duration').value);
        //        if(isNaN(duration)){
        //            alert('Invalid duration');
        //            return false;
        //        }

        var hari = parseInt($('.hari').val(), 10);
        var bln = parseInt($('.bulan').val(), 10);
        var thn = parseInt($('.tahun').val(), 10);
        if (isNaN(hari)) {
            alert('Tempoh Hari tidak betul.');
            $('.hari').focus();
            return false;
        }
        if (isNaN(bln)) {
            alert('Tempoh bulan tidak betul.');
            $('.bulan').focus();
            return false;
        }
        if (isNaN(thn)) {
            alert('Tempoh tahun tidak betul.');
            $('.tahun').focus();
            return false;
        }

        var startDate = $('#' + id).val();

        var endDt = startDate.split("/");
        var date = endDt[0];
        var month = endDt[1];
        var year = endDt[2];
        //alert(year);
        //alert(date);
        //alert(month);

        if (parseInt(month) == 2) {//feb month
            var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));

            if (parseInt(date) > 29 || (parseInt(date) == 29 && !isleap)) {
                //alert("February " + year + " doesn't have " + date + " days!");
                //return false;
            }
        }


        var myDate = new Date(year, month - 1, date - 1);
        myDate.setMonth(myDate.getMonth() + bln);
        myDate.setDate(myDate.getDate() + hari);
        myDate.setYear(myDate.getYear() + thn);
        var dd = new Date(myDate.getYear(), myDate.getMonth(), myDate.getDate());
        newDate = append(dd.getDate()) + "/" + append((dd.getMonth() + 1)) + "/" + dd.getFullYear();
        //alert(newDate);
        return newDate;
    }

    function append(x) {
        return(x < 0 || x > 9 ? "" : "0") + x
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="listUtil" />
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">
    <s:hidden name="idUrusan" value="${actionBean.idUrusan}"/>
    <div id="page_div">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Urusan
                </legend>
                <p style="color:red">
                    *Sila isi yang berkenaan sahaja untuk pembetulan.
                </p>

                <div align="center">
                    <table class="tablecloth">
                        <tr><th colspan="3">Butiran Urusan</th></tr>
                                <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 1}">
                            <tr><td colspan="3" style="color:red" align="left"><input type="checkbox" name="copyToAll" value="1" />Sila klik jika sama untuk semua hakmilik.</td></tr>
                                </c:if>
                        <tr><td>No Perserahan :</td><td colspan="2" class="s">${actionBean.hakmilikUrusan.idPerserahan}</td>
                        </tr>
                        <tr><td>Tarikh Perserahan Lama:</td>
                            <td colspan="2" class="s" ><fmt:formatDate pattern="dd/MM/yyyy hh:mm aa" value="${actionBean.hakmilikUrusan.tarikhDaftar}"/></td>
                        </tr>
                        <tr><td>Tarikh Perserahan Baru:</td>
                            <td class="s">
                                <s:text name="betulUrusan.tarikhDaftar" id = "trhDaftarBaru" formatType="date" formatPattern="dd/MM/yyyy" class="datepicker"/>
                            </td>
                        </tr>
                        <tr><td>Masa Perserahan Baru:</td>
                            <td class="s">
                                <s:select name="jam" style="width:61px">
                                    <s:option value="0">Pilih</s:option>
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
                                <s:select name="minit" style="width:61px">
                                    <s:option value="0">Pilih</s:option>
                                    <s:option value="00">00</s:option>
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
                                    <s:option value="13">13</s:option>
                                    <s:option value="14">14</s:option>
                                    <s:option value="15">15</s:option>
                                    <s:option value="16">16</s:option>
                                    <s:option value="17">17</s:option>
                                    <s:option value="18">18</s:option>
                                    <s:option value="19">19</s:option>
                                    <s:option value="20">20</s:option>
                                    <s:option value="21">21</s:option>
                                    <s:option value="22">22</s:option>
                                    <s:option value="23">23</s:option>
                                    <s:option value="24">24</s:option>
                                    <s:option value="25">25</s:option>
                                    <s:option value="26">26</s:option>
                                    <s:option value="27">27</s:option>
                                    <s:option value="28">28</s:option>
                                    <s:option value="29">29</s:option>
                                    <s:option value="30">30</s:option>
                                    <s:option value="31">31</s:option>
                                    <s:option value="32">32</s:option>
                                    <s:option value="33">33</s:option>
                                    <s:option value="34">34</s:option>
                                    <s:option value="35">35</s:option>
                                    <s:option value="36">36</s:option>
                                    <s:option value="37">37</s:option>
                                    <s:option value="38">38</s:option>
                                    <s:option value="39">39</s:option>
                                    <s:option value="40">40</s:option>
                                    <s:option value="41">41</s:option>
                                    <s:option value="42">42</s:option>
                                    <s:option value="43">43</s:option>
                                    <s:option value="44">44</s:option>
                                    <s:option value="45">45</s:option>
                                    <s:option value="46">46</s:option>
                                    <s:option value="47">47</s:option>
                                    <s:option value="48">48</s:option>
                                    <s:option value="49">49</s:option>
                                    <s:option value="50">50</s:option>
                                    <s:option value="51">51</s:option>
                                    <s:option value="52">52</s:option>
                                    <s:option value="53">53</s:option>
                                    <s:option value="54">54</s:option>
                                    <s:option value="55">55</s:option>
                                    <s:option value="56">56</s:option>
                                    <s:option value="57">57</s:option>
                                    <s:option value="58">58</s:option>
                                    <s:option value="59">59</s:option>
                                </s:select>:
                                <s:select name="saat" style="width:61px">
                                    <s:option value="0">Pilih</s:option>
                                    <s:option value="00">00</s:option>
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
                                    <s:option value="13">13</s:option>
                                    <s:option value="14">14</s:option>
                                    <s:option value="15">15</s:option>
                                    <s:option value="16">16</s:option>
                                    <s:option value="17">17</s:option>
                                    <s:option value="18">18</s:option>
                                    <s:option value="19">19</s:option>
                                    <s:option value="20">20</s:option>
                                    <s:option value="21">21</s:option>
                                    <s:option value="22">22</s:option>
                                    <s:option value="23">23</s:option>
                                    <s:option value="24">24</s:option>
                                    <s:option value="25">25</s:option>
                                    <s:option value="26">26</s:option>
                                    <s:option value="27">27</s:option>
                                    <s:option value="28">28</s:option>
                                    <s:option value="29">29</s:option>
                                    <s:option value="30">30</s:option>
                                    <s:option value="31">31</s:option>
                                    <s:option value="32">32</s:option>
                                    <s:option value="33">33</s:option>
                                    <s:option value="34">34</s:option>
                                    <s:option value="35">35</s:option>
                                    <s:option value="36">36</s:option>
                                    <s:option value="37">37</s:option>
                                    <s:option value="38">38</s:option>
                                    <s:option value="39">39</s:option>
                                    <s:option value="40">40</s:option>
                                    <s:option value="41">41</s:option>
                                    <s:option value="42">42</s:option>
                                    <s:option value="43">43</s:option>
                                    <s:option value="44">44</s:option>
                                    <s:option value="45">45</s:option>
                                    <s:option value="46">46</s:option>
                                    <s:option value="47">47</s:option>
                                    <s:option value="48">48</s:option>
                                    <s:option value="49">49</s:option>
                                    <s:option value="50">50</s:option>
                                    <s:option value="51">51</s:option>
                                    <s:option value="52">52</s:option>
                                    <s:option value="53">53</s:option>
                                    <s:option value="54">54</s:option>
                                    <s:option value="55">55</s:option>
                                    <s:option value="56">56</s:option>
                                    <s:option value="57">57</s:option>
                                    <s:option value="58">58</s:option>
                                    <s:option value="59">59</s:option>
                                    <s:option value="60">60</s:option>
                                </s:select>
                                <s:select name="ampm" style="width:80px">
                                    <s:option value="0">Pilih</s:option>
                                    <s:option value="AM">Pagi</s:option>
                                    <s:option value="PM">Petang</s:option>
                                </s:select>

                            </td>
                        </tr>

                        <tr><th>Perkara</th><th>Maklumat Lama</th><th>Maklumat Baru</th></tr>
                                <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kodPerserahan.kod eq 'SC'}">
                                    <c:set var="disabled" value="false"/>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMT' || actionBean.permohonan.kodUrusan.kod eq 'GD'}">
                                        <c:set var="disabled" value="false"/>
                                    </c:if>
                            <tr><td>No Jilid :</td>
                                <td class="s" >${actionBean.fd.noJilid}</td>
                                <td class="s">
                                    <s:text name="noJilid"/>
                                </td></tr>
                            <tr><td>No Folio :</td>
                                <td class="s" >${actionBean.fd.noFolio}</td>
                                <td class="s">
                                    <s:text name="noFolio"/>
                                </td></tr>
                            <tr><td>Amaun (RM) :</td>
                                <td class="s" >${actionBean.mohonUrusan.perjanjianAmaun}</td>
                                <td class="s">
                                    <s:text name="perjanjianAmaun" id="pjanjianAmaun"
                                            onchange="convert(this.value, this.id);doCalculateDutiStem('pjanjianDutiSetam', this.value);"
                                            formatPattern="#,###.00"/>
                                </td></tr>

                            <tr><td>Duti Setem (RM):</td>
                                <td class="s" >${actionBean.mohonUrusan.perjanjianDutiSetem}</td>
                                <td class="s">
                                    <s:text name="perjanjianDutiStem" id="pjanjianDutiSetam"
                                            onchange="convert(this.value, this.id);" formatPattern="#,###.00"/>
                                </td></tr>

                            <tr><td>No Mahkamah :</td>
                                <td class="s" >${actionBean.mohonUrusan.perjanjianNoRujukan}</td>
                                <td class="s">
                                    <s:text name="perjanjianNoRujukan"/>
                                </td></tr>
                            <tr><td>No Fail/ID Permohonan :</td>
                                <td class="s" >${actionBean.pkl.noFail}</td>
                                <td class="s">
                                    <s:text name="noFail"/>
                                </td></tr>
                            <tr><td>Tarikh Penyaksian :</td>
                                <td class="s" ><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.mohonUrusan.tarikhSaksi}"/></td>
                                <td class="s">
                                    <s:text name="tarikhSaksi" class="datepicker"/>
                                </td></tr>
                                <c:if test="${!fn:startsWith(actionBean.permohonan.kodUrusan.kod,'PJ')}">                
                                    <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod ne 'PMT'}"> <!--add by azri 11/7/2013: if PMT, don't show this-->
                                    <tr><td>Tempoh :</td>
                                        <td class="s"> ${actionBean.pkl.tempohTahun}<c:if test="${actionBean.pkl.tempohTahun ne null}"> tahun </c:if>${actionBean.pkl.tempohBulan} <c:if test="${actionBean.pkl.tempohBulan ne null}">bulan</c:if></td>
                                            <td class="s">
                                            <s:text name="tempohTahun" class="tahun" id = "tahun" style="width:50px" onchange="doCalcEndDate('perintahTarikhKuatkuasa');"/> tahun &nbsp;
                                            <s:text name="tempohBulan" class="bulan" id = "bulan" style="width:50px" onchange="doCalcEndDate('perintahTarikhKuatkuasa');"/> bulan &nbsp;
                                        </td></tr>
                                    <tr><td>Tarikh Mula :</td>
                                        <td class="s"> <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.pkl.tarikhKuatkuasa}"/></td>
                                        <td class="s">
                                            <s:text name="perintahTarikhKuatkuasa"  id = "perintahTarikhKuatkuasa" class="datepicker" onchange="doCalcEndDate(this.id);"/>
                                        </td></tr>
                                    <tr><td>Tarikh Tamat:</td>
                                        <td class="s"> <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.pkl.tarikhTamat}"/></td>
                                        <td class="s">
                                            <s:text name="tarikhTamat" id = "tarikhTamat" formatType="date" formatPattern="dd/MM/yyyy" class="datepicker"/>
                                        </td></tr>
                                    </c:if>
                                     <tr>
                                    <td>Status:</td>

                                    <td class="s">
                                        <c:if test="${actionBean.hakmilikUrusan.aktif == 'Y'}">
                                            Aktif
                                        </c:if>
                                        <c:if test="${actionBean.hakmilikUrusan.aktif == 'T'}">
                                            Batal
                                        </c:if>
                                    </td>

                                    <td class="s">
                                        <s:select name="aktif">
                                            <s:option value="0">Pilih</s:option>
                                            <s:option value="Y">Aktif</s:option>
                                            <s:option value="T">Batal</s:option>
                                            </td>

                                    </s:select>
                                </tr>

                            </c:if>
                            <tr><td colspan="3">
                                    <div align="center">
                                        <s:button name="saveUrusanSCBatal" value="Simpan" class="btn" onmouseover="this.style.cursor='pointer';" onclick="save(this.name, this.form, '${actionBean.idUrusan}')"/>
                                        <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>
                                    </div>
                                </td></tr>
                            </c:if>
                            <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kodPerserahan.kod eq 'N'}">
                            <tr><td>No. Rujukan Fail:</td>
                                <td class="s" >${actionBean.pkl.noFail}</td>
                                <td class="s">
                                    <s:text name="noFail" onkeyup="this.value=this.value.toUpperCase();"/>
                                </td></tr>
                                  <tr>
                                    <td>Status:</td>

                                    <td class="s">
                                        <c:if test="${actionBean.hakmilikUrusan.aktif == 'Y'}">
                                            Aktif
                                        </c:if>
                                        <c:if test="${actionBean.hakmilikUrusan.aktif == 'T'}">
                                            Batal
                                        </c:if>
                                    </td>

                                    <td class="s">
                                        <s:select name="aktif">
                                            <s:option value="0">Pilih</s:option>
                                            <s:option value="Y">Aktif</s:option>
                                            <s:option value="T">Batal</s:option>
                                            </td>

                                    </s:select>
                                </tr>
                                <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'N7A'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'N7B'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'N6A'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ADMNS'}">
                                <tr><td>Tarikh Disampai :</td>
                                    <td class="s"><s:text name="pkl.tarikhSidang" formatType="datetime" formatPattern="dd/MM/yyyy hh:mm aa"
                                            disabled="true"/></td>
                                    <td class="s">
                                        <s:text name="tarikhSidang" class="datepicker"/>
                                    </td></tr>
                                </c:if>
                                <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'N8A'}">
                                <tr><td>Tarikh Notis :</td>
                                    <td class="s"><s:text name="pkl.tarikhRujukan" formatType="datetime" formatPattern="dd/MM/yyyy hh:mm aa"
                                            disabled="true"/></td>
                                    <td class="s">
                                        <s:text name="tarikhRujukan" class="datepicker"/>
                                    </td></tr>
                                </c:if>

                            <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'N6A'}">
                                <tr><td>Tarikh Notis / Warta :</td>
                                    <td class="s"><s:text name="pkl.tarikhRujukan" formatType="datetime" formatPattern="dd/MM/yyyy hh:mm aa"
                                            disabled="true"/></td>
                                    <td class="s">
                                        <s:text name="tarikhRujukan" class="datepicker"/>
                                    </td></tr>
                                <tr><td>Nombor Warta :</td>
                                    <td class="s">${actionBean.pkl.noRujukan}</td>
                                    <td class="s">
                                        <s:text name="noRujukan" onkeyup="this.value=this.value.toUpperCase();"/>
                                    </td></tr>
                                </c:if>
                                <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABT-K'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABT-D'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABTKB'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'CL'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'FGT1'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'FGT2'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'FGT3'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'FGT4'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'FGT5'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'FGT6'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'FGT7'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'FGT8'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'FGT9'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'HMV'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IGSA'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IGSA5'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IGSA6'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IROAB'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'N7A'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'N7B'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'N8A'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'N8AB'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PHKK'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PHSK'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PCT'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PPKR'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ACT'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABTS'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ITP'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PTB'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PTP'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ITB'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IPM'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IRM'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IROA'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IRTB'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ITBB'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'MAJD'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'MAJB'}">

                                <tr><td>Tarikh Warta :</td>
                                    <td class="s"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.pkl.tarikhRujukan}"/></td>
                                    <td class="s">
                                        <s:text name="tarikhRujukan" class="datepicker"/>
                                    </td></tr>
                                <tr><td>Nombor Warta :</td>
                                    <td class="s">${actionBean.pkl.noRujukan}</td>
                                    <td class="s">
                                        <s:text name="noRujukan" onkeyup="this.value=this.value.toUpperCase();"/>
                                    </td></tr>

                                <c:if test="${actionBean.betulUrusan.cukaiBaru eq null}">
                                    <tr><td>Cukai:</td>
                                        <td class="s">${actionBean.cukaiBaru}</td>
                                        <td class="s">
                                            <s:text name="cukai"  onkeyup="this.value=this.value.toUpperCase();"/>
                                        </td></tr>

                                </c:if>

                                <c:if test="${actionBean.betulUrusan.cukaiBaru ne null}">
                                    <tr><td>Cukai:</td>
                                        <td class="s">${actionBean.cukaiBaru}</td>
                                        <td class="s">
                                            <s:text name="betulUrusan.cukaiBaru" value ="${actionBean.betulUrusan.cukaiBaru}" onkeyup="this.value=this.value.toUpperCase();"/>
                                        </td></tr>



                                </c:if>
                                <c:if test="${actionBean.luas eq null}">
                                    <tr><td>Luas:</td>
                                        <td class="s">${actionBean.luas}</td>
                                        <td class="s">
                                            <s:text name="betulUrusan.luasTerlibat"  onkeyup="this.value=this.value.toUpperCase();"/>
                                        </td></tr>

                                </c:if>

                                <c:if test="${actionBean.luas ne null}">
                                    <tr><td>Luas:</td>
                                        <td class="s">${actionBean.luas}</td>
                                        <td class="s">
                                            <s:text name="betulUrusan.luasTerlibat" value ="${actionBean.luasTerlibat}" onkeyup="this.value=this.value.toUpperCase();"/>
                                        </td></tr>



                                </c:if>


                                <tr>
                                    <td>Unit Luas :</td><td class="s">${actionBean.strKodUOM}</td><td>
                                        <s:select id="val1" name="strKodUOM" value="${actionBean.betulUrusan.kodUnitLuas.kod}">
                                            <c:if test="${actionBean.betulUrusan.kodUnitLuas.kod == null}">
                                                <s:option value="">Sila Pilih</s:option>
                                            </c:if>
                                            <c:if test="${actionBean.betulUrusan.kodUnitLuas.kod != null}">
                                                <s:option value="">${actionBean.betulUrusan.kodUnitLuas.nama}</s:option>
                                            </c:if>
                                            <s:options-collection id="a1" collection="${listUtil.senaraiKodUOMByLuas}" label="nama"  value="kod"/>
                                        </s:select></td>  
                                </tr>

                                <tr>
                                    <td>Status:</td>

                                    <td class="s">
                                        <c:if test="${actionBean.hakmilikUrusan.aktif == 'Y'}">
                                            Aktif
                                        </c:if>
                                        <c:if test="${actionBean.hakmilikUrusan.aktif == 'T'}">
                                            Batal
                                        </c:if>
                                    </td>

                                    <td class="s">
                                        <s:select name="aktif">
                                            <s:option value="0">Pilih</s:option>
                                            <s:option value="Y">Aktif</s:option>
                                            <s:option value="T">Batal</s:option>
                                            </td>

                                    </s:select>
                                </tr>




                            </c:if>

                            <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'HTBKR'
                                          or actionBean.hakmilikUrusan.kodUrusan.kod eq 'SBTL'}">
                                  <tr><td>No. Mesyuarat :</td>
                                      <td class="s">${actionBean.pkl.noSidang}</td>
                                      <td class="s">
                                          <s:text name="noSidang" onkeyup="this.value=this.value.toUpperCase();"/>
                                      </td></tr>
                                  <tr><td>Tarikh Mesyuarat :</td>
                                      <td class="s"><s:text name="pkl.tarikhSidang" formatType="datetime" formatPattern="dd/MM/yyyy hh:mm aa"
                                              disabled="true"/></td>
                                      <td class="s">
                                          <s:text name="tarikhSidang" class="datepicker"/>
                                      </td></tr>
                                  </c:if>
                                  <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'MCLL'
                                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'SBKSL'
                                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABT-K'
                                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'TSSKL'
                                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABTS'
                                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PBCTL'
                                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PSL'
                                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'SSKPL'
                                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'SBSTL'
                                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'SBTL'}">

                                  <tr><td>Yang Meluluskan :</td>
                                      <td class="s">${actionBean.pkl.noSidang}</td>
                                      <td class="s">
                                          <s:text name="noSidang" onkeyup="this.value=this.value.toUpperCase();"/>
                                      </td></tr>
                                  <tr><td>Tarikh Kelulusan :</td>
                                      <td class="s"><s:text name="pkl.tarikhSidang" formatType="datetime" formatPattern="dd/MM/yyyy hh:mm aa"
                                              disabled="true"/></td>
                                      <td class="s">
                                          <s:text name="tarikhSidang" class="datepicker"/>
                                      </td></tr>
                                  </c:if>
                                  <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'PRPMB'
                                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'N8AB'}">
                                  <tr><td>No. Mahkamah:</td>
                                      <td class="s">${actionBean.pkl.noSidang}</td>
                                      <td class="s">
                                          <s:text name="noSidang" onkeyup="this.value=this.value.toUpperCase();"/>
                                      </td></tr>
                                  <tr><td>Tarikh Mahkamah :</td>
                                      <td class="s"><s:text name="pkl.tarikhSidang" formatType="datetime" formatPattern="dd/MM/yyyy hh:mm aa"
                                              disabled="true"/></td>
                                      <td class="s">
                                          <s:text name="tarikhSidang" class="datepicker"/>
                                      </td></tr>
                                  <tr><td>Nama Mahkamah :</td>
                                      <td class="s"><s:textarea name="pkl.catatan" disabled="true"/></td>
                                      <td class="s">
                                          <s:textarea name="catatan" onkeyup="this.value=this.value.toUpperCase();"/>
                                      </td></tr>
                                  </c:if>
                                  <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'HTBKR'
                                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'HTB'}">
                                  <tr><td>No. Buku :</td>
                                      <td class="s">${actionBean.pkl.noRujukan}</td>
                                      <td class="s">
                                          <s:text name="noRujukan" class="datepicker"/>
                                      </td></tr>
                                  <tr><td>Tarikh Buku :</td>
                                      <td class="s"><s:text name="pkl.tarikhRujukan" formatType="datetime" formatPattern="dd/MM/yyyy hh:mm aa"
                                              disabled="true"/></td>
                                      <td class="s">
                                          <s:text name="tarikhRujukan" class="datepicker"/>
                                      </td></tr>
                                  <tr><td>Nama (Perbadanan Pengurusan) :</td>
                                      <td class="s"><s:textarea name="pkl.catatan" disabled="true"/></td>
                                      <td class="s">
                                          <s:textarea name="catatan"/>
                                      </td></tr>
                                  <tr><td>Alamat :</td>
                                      <td class="s"> <s:textarea name="pkl.ulasan" disabled="true"></s:textarea></td>
                                          <td class="s">
                                          <s:textarea name="ulasan"></s:textarea>
                                          </td></tr>
                                  </c:if>

                            <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'LTS'}">
                                <tr><td>Tempoh :</td>
                                    <td class="s"><s:text name="pkl.tempohTahun" style="width:25px;"/>Tahun &nbsp;&nbsp;

                                        <s:text name="pkl.tempohBulan" style="width:25px;"/>Bulan &nbsp;&nbsp;

                                        <s:text name="pkl.tempohHari" style="width:25px;"/>Hari &nbsp;&nbsp;</td>
                                    <td class="s">
                                        <s:text name="tempohTahun" style="width:25px;"/>Tahun &nbsp;&nbsp;

                                        <s:text name="tempohBulan" style="width:25px;"/>Bulan &nbsp;&nbsp;

                                        <s:text name="tempohHari" style="width:25px;"/>Hari &nbsp;&nbsp;</td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'IGSA'
                                          or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IGSA5'
                                          or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IGSA6'
                                          or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ITP'
                                          or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ITB'
                                          or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IRM'
                                          or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IPM'
                                          or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IROA'
                                          or actionBean.hakmilikUrusan.kodUrusan.kod eq 'IRTB'
                                          or actionBean.hakmilikUrusan.kodUrusan.kod eq 'RKSR'
                                          or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PTP'
                                          or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KB'
                                          or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KRM'}">

                                  <tr><td>Kawasan :</td>
                                      <td class="s">${actionBean.pkl.item}</td>
                                      <td class="s">
                                          <s:text name="majlis" onkeyup="this.value=this.value.toUpperCase();"/>
                                      </td></tr>
                                  </c:if>
                                  <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'MAJD'}">
                                  <tr><td>Kawasan Majlis Daerah :</td>
                                      <td class="s">${actionBean.pkl.item}</td>
                                      <td class="s">
                                          <s:text name="majlis" onkeyup="this.value=this.value.toUpperCase();"/>
                                      </td></tr>
                                  </c:if>
                                  <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'PPKR'}">
                                  <tr><td>Perbadanan :</td>
                                      <td class="s">${actionBean.pkl.noSidang}</td>
                                      <td class="s">
                                          <s:text name="noSidang" onkeyup="this.value=this.value.toUpperCase();"/>
                                      </td></tr>
                                  </c:if>
                                  <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'MAJB'}">
                                  <tr><td>Kawasan Majlis Perbandaran :</td>
                                      <td class="s">${actionBean.pkl.item}</td>
                                      <td class="s">
                                          <s:text name="majlis" onkeyup="this.value=this.value.toUpperCase();"/>
                                      </td></tr>
                                  </c:if>
                                  <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABTB'
                                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PHKK'}">
                                  <tr><td>Sebab :</td>
                                      <td class="s"><s:textarea name="pkl.catatan" disabled="true"/></td>
                                      <td class="s">
                                          <s:textarea name="catatan"/>
                                      </td></tr>
                                  </c:if>
                                  <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'HLLS'}">
                                  <tr><td>Bagi Kegunaan:</td>
                                      <td class="s"><s:textarea name="pkl.catatan" disabled="true"/></td>
                                      <td class="s">
                                          <s:textarea name="catatan"/>
                                      </td></tr>
                                  </c:if>
                                  <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'SHSK'
                                                or actionBean.hakmilikUrusan.kodUrusan.kod eq 'SHKK'}">
                                  <tr><td>Versi :</td>
                                      <td class="s"><s:textarea name="pkl.catatan" disabled="true"/></td>
                                      <td class="s">
                                          <s:textarea name="catatan"/>
                                      </td></tr>
                                  <tr><td>Kepada :</td>
                                      <td class="s">${actionBean.pkl.noSidang}</td>
                                      <td class="s">
                                          <s:text name="noSidang"/>
                                      </td></tr>
                                  <tr><td>No. Pengenalan :</td>
                                      <td class="s"> <s:textarea name="pkl.ulasan" disabled="true"></s:textarea></td>
                                          <td class="s">
                                          <s:textarea name="ulasan"></s:textarea>
                                          </td></tr>
                                  </c:if>
                            <tr><td colspan="3">
                                    <div align="center">
                                        <s:button name="saveUrusanNBatal" value="Simpan" class="btn" onmouseover="this.style.cursor='pointer';" onclick="save(this.name, this.form, '${actionBean.idUrusan}')"/>
                                        <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>
                                    </div>
                                </td></tr>
                            </c:if>
                            <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kodPerserahan.kod eq 'B'}">
                            <tr><td>Jenis Perintah :</td>
                                <td class="s">${actionBean.pkl.kodPerintah.nama}</td>
                                <td class="s">
                                    <s:select name="kodPerintah" id="order" onchange="doselectOrder();" style="width:200px">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodPerintah}" value="kod" label="nama"/>
                                    </s:select>
                                </td></tr>
                            <tr><td>No Fail :</td>
                                <td class="s">${actionBean.pkl.noFail}</td>
                                <td class="s">
                                    <s:text name="noFail" style="width:300px" onkeyup="this.value=this.value.toUpperCase();"/>
                                </td></tr>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PNPA'}">
                                <tr><td>No Surat Amanah/No Perintah/Saman Pemula: :</td>
                                    <td class="s">${actionBean.pkl.noRujukan}</td>
                                    <td class="s">
                                        <s:text name="noRujukan" style="width:300px"/>
                                    </td>
                                </tr>
                            </c:if>
                            <tr><td>No Mahkamah :</td>
                                <td class="s">${actionBean.pkl.noSidang}</td>
                                <td class="s">
                                    <s:text name="noSidang" style="width:300px" onkeyup="this.value=this.value.toUpperCase();"/>
                                </td><noFail/tr>
                                <tr><td>Mahkamah/Pejabat Tanah Daerah :</td>
                                    <td class="s">${actionBean.pkl.namaSidang}</td>
                                    <td class="s">
                                        <s:text name="namaSidang" style="width:300px" onkeyup="this.value=this.value.toUpperCase();"/>
                                    </td></tr>
                                <tr><td>Tarikh Perintah :</td>
                                    <td class="s"><fmt:formatDate pattern="dd/MM/yyyy hh:mm aa" value="${actionBean.pkl.tarikhSidang}"/></td>
                                    <td class="s">
                                        <s:text name="tarikhSidang"  class="datepicker" style="width:300px"/>
                                    </td></tr>
                                <tr><td>Sebab Perintah :</td>
                                    <td class="s"> ${actionBean.pkl.ulasan}</td>
                                    <td class="s">
                                        <s:textarea name="perintahSebab" style="width:300px"/>
                                    </td></tr>
                                <tr><td>Tempoh :</td>
                                    <td class="s"> ${actionBean.pkl.tempohTahun}<c:if test="${actionBean.pkl.tempohTahun ne null}">tahun</c:if>${actionBean.pkl.tempohBulan} <c:if test="${actionBean.pkl.tempohBulan ne null}">bulan</c:if> ${actionBean.pkl.tempohHari} <c:if test="${actionBean.pkl.tempohHari ne null}">hari</c:if></td>
                                        <td class="s">
                                        <s:text name="tempohTahun" class="tahun" style="width:50px"/> tahun &nbsp;
                                        <s:text name="tempohBulan" class="bulan" style="width:50px"/> bulan &nbsp;
                                        <s:text name="tempohHari" class="hari" style="width:50px"/> hari
                                    </td></tr>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PNPA'}">
                                    <tr><td>Tarikh Rujukan :</td>
                                        <td class="s"> <fmt:formatDate pattern="dd/MM/yyyy hh:mm aa" value="${actionBean.pkl.tarikhRujukan}"/></td>
                                        <td class="s">
                                            <s:text name="tarikhRujukan" class="datepicker"  style="width:300px" onchange="doCalcEndDate(this.id);"/>
                                        </td></tr>
                                    <tr><td>Tarikh Kuatkuasa :</td>
                                        <td class="s"> <fmt:formatDate pattern="dd/MM/yyyy hh:mm aa" value="${actionBean.pkl.tarikhKuatkuasa}"/></td>
                                        <td class="s">
                                            <s:text name="perintahTarikhKuatkuasa"  class="datepicker" style="width:300px" onchange="doCalcEndDate(this.id);"/>
                                        </td></tr>
                                    </c:if>
                                <tr><td>Tarikh Tamat :</td>
                                    <td class="s"> <fmt:formatDate pattern="dd/MM/yyyy hh:mm aa" value="${actionBean.pkl.tarikhTamat}"/></td>
                                    <td class="s">
                                        <s:text name="tarikhTamat" class="datepicker"  style="width:300px" onchange="doCalcEndDate(this.id);"/>
                                    </td></tr>
                                 <tr>
                                    <td>Status:</td>

                                    <td class="s">
                                        <c:if test="${actionBean.hakmilikUrusan.aktif == 'Y'}">
                                            Aktif
                                        </c:if>
                                        <c:if test="${actionBean.hakmilikUrusan.aktif == 'T'}">
                                            Batal
                                        </c:if>
                                    </td>

                                    <td class="s">
                                        <s:select name="aktif">
                                            <s:option value="0">Pilih</s:option>
                                            <s:option value="Y">Aktif</s:option>
                                            <s:option value="T">Batal</s:option>
                                            </td>

                                    </s:select>
                                </tr>

                                <tr><td colspan="3">
                                        <div align="center">
                                            <s:button name="saveUrusanBbatal" value="Simpan" class="btn" onmouseover="this.style.cursor='pointer';" onclick="save(this.name, this.form, '${actionBean.idUrusan}')"/>
                                            <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>
                                        </div>
                                    </td></tr>
                                </c:if>
                    </table>
                    <br>
                    <br/>
                </div>

            </fieldset>

            <c:if test="${fn:length(actionBean.hakmilikPermohonanList) < 0}">
                <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kodPerserahan.kod eq 'N'}">
                    <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABT-D'
                                  or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABT'
                                  or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABT-A'
                                  or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABT-K'
                                  or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABTS'
                                  or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABTKB'
                                  or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABTBH'
                                  or actionBean.hakmilikUrusan.kodUrusan.kod eq 'EUBS'
                                  or actionBean.hakmilikUrusan.kodUrusan.kod eq 'HLTE'
                                  or actionBean.hakmilikUrusan.kodUrusan.kod eq 'MCLL'
                                  or actionBean.hakmilikUrusan.kodUrusan.kod eq 'PCT'
                                  or actionBean.hakmilikUrusan.kodUrusan.kod eq 'SBSTL'
                                  or actionBean.hakmilikUrusan.kodUrusan.kod eq 'TSSKL'
                                  or actionBean.hakmilikUrusan.kodUrusan.kod eq 'KCL'
                                  or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ADMNB'

                          }">


                        <fieldset class="aras1">
                            <legend>
                                Baiki Maklumat Berkaitan Hakmilik
                            </legend>

                            <div class="content" align="center">

                                <%-- Luas and Cukai--%>
                                <c:if test="${ actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABT'
                                               or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABT-A'
                                               or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABTKB'
                                               or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABTBH'
                                               or actionBean.hakmilikUrusan.kodUrusan.kod eq 'SBSTL'}">

                                      <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="sizeHakmilikPermohonan"/>
                                      <display:table class="tablecloth" requestURI="/common/maklumat_hakmilik_permohonan" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
                                          <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                          <display:column title="Luas / Unit Asal">
                                              ${line.hakmilik.luas}
                                              ${line.hakmilik.kodUnitLuas.nama}
                                          </display:column>
                                          <display:column title="Luas / Unit Terlibat" >
                                              <s:text name="luasTerlibat" formatPattern="###0.0000" size="5"/>
                                              <s:select  style="text-transform:uppercase"  name="strKodUOM">
                                                  <s:option value=" ${line.kodUnitLuas.kod}"> ${line.kodUnitLuas.nama}</s:option>
                                                  <s:options-collection collection="${listUtil.senaraiKodUOMByJarak}" label="nama" value="kod"/>
                                              </s:select>
                                          </display:column>
                                          <display:column title="Cukai">
                                              RM ${line.hakmilik.cukai}
                                          </display:column>
                                          <display:column title="Cukai Baru">
                                              RM <s:text name="cukaiBaru" formatPattern="###0" value="${line.cukaiBaru}"/>
                                          </display:column>
                                      </display:table>
                                      <br/>
                                </c:if>

                                <%-- Luas and Cukai abt-d--%>
                                <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'ABT-D'}">

                                    <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="sizeHakmilikPermohonan"/>
                                    <display:table class="tablecloth" requestURI="/common/maklumat_hakmilik_permohonan" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column title="Luas / Unit Asal">
                                            ${line.hakmilik.luas}
                                            ${line.hakmilik.kodUnitLuas.nama}
                                        </display:column>
                                        <display:column title="Luas / Unit Terlibat" ><%--strKodUOM[${line_rowNum-1}]"--%>
                                            <s:text name="luasTerlibat" formatPattern="###0.0000" size="10"/>
                                            <s:select  style="text-transform:uppercase"  name="strKodUOM">
                                                <s:option value=" ${line.kodUnitLuas.kod}"> ${line.kodUnitLuas.nama}</s:option>
                                                <s:options-collection collection="${listUtil.senaraiKodUOMByJarak}" label="nama" value="kod"/>
                                            </s:select>
                                        </display:column>
                                        <display:column title="Cukai">
                                            RM ${line.hakmilik.cukai}
                                        </display:column>
                                    </display:table>
                                    <br/>
                                </c:if>

                                <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'EUBS'}">
                                    <%--EUBS--%>

                                    <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="sizeHakmilikPermohonan"/>
                                    <display:table class="tablecloth" requestURI="/common/maklumat_hakmilik_permohonan" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">

                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column title="Kod Syarat Nyata" class="tooltips">&nbsp;<img  alt='Klik Untuk Bantuan' border='0' src='${pageContext.request.contextPath}/pub/images/semak.gif'
                                                    id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="popupCarian('cariKodSyaratNyata&kodSyaratNyata=', '${line_rowNum-1}', 'syarat')"/>
                                            <s:text name="strKodSyaratNyata" id="strKodSyaratNyata[${line_rowNum-1}]"  maxlength="7"/> &nbsp;&nbsp;&nbsp; Kod Disimpan : ${line.syaratNyataBaru.kodSyarat}
                                        </display:column>
                                        <display:column title="Kod Sekatan">&nbsp;<img  alt='Klik Untuk Bantuan' border='0' src='${pageContext.request.contextPath}/pub/images/semak.gif'
                                                    id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="popupCarian('cariKodSekatan&kodSekatan=', '${line_rowNum-1}', 'sekatan')"/>
                                            <s:text name="strKodSekatan" id="strKodSekatan[${line_rowNum-1}]" maxlength="7" />&nbsp;&nbsp;&nbsp; Kod Disimpan : ${line.sekatanKepentinganBaru.kodSekatan}
                                        </display:column>
                                    </display:table>
                                    <br/>

                                </c:if>
                                <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'HLTE'
                                              or actionBean.hakmilikUrusan.kodUrusan.kod eq 'ADMRL'}">
                                    <%--HLTE Luas--%>
                                    <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="sizeHakmilikPermohonan"/>
                                    <display:table class="tablecloth" requestURI="/common/maklumat_hakmilik_permohonan" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column title="Luas / Unit Asal">
                                            ${line.hakmilik.luas}
                                            ${line.hakmilik.kodUnitLuas.nama}
                                        </display:column>
                                        <display:column title="Luas / Unit Terlibat" >
                                            <s:text name="luasTerlibat" formatPattern="###0.0000" size="10"/>
                                            <s:select  style="text-transform:uppercase"  name="strKodUOM">
                                                <s:option value=" ${line.kodUnitLuas.kod}"> ${line.kodUnitLuas.nama}</s:option>
                                                <s:options-collection collection="${listUtil.senaraiKodUOMByJarak}" label="nama" value="kod"/>
                                            </s:select>
                                        </display:column>

                                    </display:table>
                                    <br/>

                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCT'
                                              or actionBean.permohonan.kodUrusan.kod eq 'KCL'}">
                                    <%--PCT/KCL Cukai Sahaj--%>

                                    <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="sizeHakmilikPermohonan"/>
                                    <display:table class="tablecloth"  requestURI="/common/maklumat_hakmilik_permohonan" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column title="Cukai">
                                            RM ${line.hakmilik.cukai}
                                        </display:column>
                                        <display:column title="Cukai Baru">
                                            RM <s:text name="cukaiBaru" formatPattern="###0" value="${line.cukaiBaru}" id="cukai${line_rowNum-1}"/>
                                        </display:column>
                                    </display:table>
                                    <br/>

                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSSKL'
                                              or actionBean.permohonan.kodUrusan.kod eq 'SSKPL'}">
                                    <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="sizeHakmilikPermohonan"/>
                                    <display:table class="tablecloth" requestURI="/common/maklumat_hakmilik_permohonan" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column title="Kod Syarat Nyata" class="tooltips">&nbsp;<img  alt='Klik Untuk Bantuan' border='0' src='${pageContext.request.contextPath}/pub/images/semak.gif'
                                                    id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="popupCarian('cariKodSyaratNyata&kodSyaratNyata=', '${line_rowNum-1}', 'syarat')"/>
                                            <s:text name="strKodSyaratNyata" id="strKodSyaratNyata[${line_rowNum-1}]"  maxlength="7"/> <br/> Kod Disimpan : ${line.syaratNyataBaru.kodSyarat}
                                        </display:column>
                                        <display:column title="Kod Sekatan">&nbsp;<img  alt='Klik Untuk Bantuan' border='0' src='${pageContext.request.contextPath}/pub/images/semak.gif'
                                                    id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="popupCarian('cariKodSekatan&kodSekatan=', '${line_rowNum-1}', 'sekatan')"/>
                                            <s:text name="strKodSekatan" id="strKodSekatan[${line_rowNum-1}]" maxlength="7" /><br/> Kod Disimpan : ${line.sekatanKepentinganBaru.kodSekatan}
                                        </display:column>
                                        <display:column title="Kategori Tanah">
                                            <s:select  style="text-transform:uppercase"  name="strKodKategori[${line_rowNum-1}]">
                                                <s:option value=" ${line.jenisPenggunaanTanah.kod}"> ${line.jenisPenggunaanTanah.nama}</s:option>
                                                <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod"/>
                                            </s:select>
                                        </display:column>
                                        <display:column title="Cukai Baru">
                                            RM <s:text name="cukaiBaru" formatPattern="###0" value="${line.cukaiBaru}" id="cukai${line_rowNum-1}"/>
                                        </display:column>
                                    </display:table>
                                    <br/>

                                </c:if>
                                <p>
                                    <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 0}">
                                        <s:button name="simpanBerkelompok" value="Simpan" class="btn" onmouseover="this.style.cursor='pointer';" onclick="save(this.name, this.form, '${actionBean.idUrusan}')"/>
                                        <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>
                                    </c:if>
                                </p>
                                <br>
                            </c:if>
                        </c:if>
                    </div>
                </fieldset>
            </c:if>
        </div>
    </div>

    <!--    <div id="page_div">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Pihak Berkepentingan 
                    </legend>
                    <br>
                    <p style="color:red">
                        *Pastikan Kemaskini Dibuat Pihak Berkepentingan Yang Betul
                    </p>
                    
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <legend>Oleh</legend>
                            <div class="content" align="center">
    <display:table class="tablecloth" name="${actionBean.senaraiPemohon}" cellpadding="0" cellspacing="0" id="line"
                   requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
        <display:column property="pihak.nama" title="Nama" class="nama"/>
        <display:column property="permohonan.idPermohonan" title="Id Permohonan" class="nama"/>
        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
        <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
        <display:column title="Tarikh Pemilikan Tanah"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/></display:column>
    </display:table>
    </div>
    </fieldset>
    
    </div>
    </fieldset>
    </div>
    
    <div id="page_div">
    <div class="subtitle">
    <fieldset class="aras1">
    
    <div class="subtitle">
    <fieldset class="aras1">
    <legend>Kepada</legend>
    <div class="content" align="center">
    <display:table class="tablecloth" name="${actionBean.senaraiMohonPihak}" cellpadding="0" cellspacing="0" id="line"
                   requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
        <display:column property="pihak.nama" title="Nama" class="nama"/>
        <display:column property="permohonan.idPermohonan" title="Id Permohonan" class="nama"/>
        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
        <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
        <display:column title="Tarikh Pemilikan Tanah"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/></display:column>
    </display:table>
    </div>
    </fieldset>
    
    </div>
    </fieldset>
    </div>
    </div>-->
</s:form>