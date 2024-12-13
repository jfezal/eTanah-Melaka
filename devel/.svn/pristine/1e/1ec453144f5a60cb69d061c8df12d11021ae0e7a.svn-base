<%-- 
    Document   : Saman_Pemula_Sek8
    Created on : Jul 26, 2011, 11:44:10 AM
    Author     : Rajesh
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html;charset=windows-1252" import="java.util.*" import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
$(document).ready( function(){
$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
$(".datepicker1").datepicker({dateFormat: 'yy'});
});

    function addRow1(tableID) {
        document.getElementById('rowCount1').value = 1;
        var table = document.getElementById(tableID);

        var rowCount1 = table.rows.length;
        var row = table.insertRow(rowCount1);
        var cell2 = row.insertCell(0);
        eDIV = document.createElement("b");
        // add the text "hello world" to the div with createTextNode
        eDIV.appendChild(document.createTextNode("1." +(rowCount1+1)));
        // append your newly created DIV element to an already existing element.
        cell2.appendChild(eDIV);

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan1"+(rowCount1+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan1"+(rowCount1+1);
        element1.id ="kandungan1"+(rowCount1+1);
        cell1.appendChild(element1);
        document.getElementById('rowCount1').value=rowCount1 +1;
    }

    function deleteRow1(formPtg,form1)
    {
        var i = document.getElementById('rowCount1').value;
        var x= document.getElementById('dataTable1').rows[i-1].cells;
        var idKandungan = x[0].innerHTML;

        if (document.getElementById('idKandungan1'+(i)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/samanPemula_sek8?deleteSingle&idKandungan='
            +idKandungan+'&formPtg='+formPtg+'&form1='+form1;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        }

        document.getElementById('dataTable1').deleteRow(i-1);
        document.getElementById('rowCount1').value = i -1;
    }

    function validation() {


    if($("#tarikhIkrar").val() == ""){
           alert('Sila pilih " PADA " terlebih dahulu.');
                $("#tarikhIkrar").focus();
                return false;
    }
    return true;
    }

function ajaxLink(link, update) {
$.get(link, function(data) {
$(update).html(data);
$(update).show();
});
return false;
}

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.SamanPemulaSek8ActionBean" name="form2">
<div id="hakmilik_details">
    <s:messages/>
    <s:errors/>
    <s:hidden name="disable" value="${actionBean.disable}"/>
    <fieldset class="aras1">
    <div align="center"><br /><br />
    <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}" pagesize="5" cellpadding="0" cellspacing="0"
                   requestURI="/pengambilan/samanPemula_sek8" id="line">
        <display:column title="No" sortable="true">${line_rowNum}</display:column>
        <display:column title="Id Hakmilik">
            <s:link beanclass="etanah.view.stripes.pengambilan.SamanPemulaSek8ActionBean"
                    event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                <s:param name="disable" value="${actionBean.disable}"/>
                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}</s:link>
        </display:column>
        <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
        <%--<display:column title="Tuan Tanah" >
            <c:set value="1" var="count"/>
            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                <table border="0">
                    <tr>
                        <td><c:out value="${count}"/>)</td>
                        <td>
                            <s:link beanclass="etanah.view.stripes.pengambilan.SamanPemulaSek8ActionBean"
                                    event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="disable" value="${actionBean.disable}"/>
                                <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                <c:out value="${senarai.pihak.nama}"/>
                            </s:link>
                        </td>
                        <td>(<c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/>)</td>
                    </tr>
                </table>
                        <c:set value="${count + 1}" var="count"/>
            </c:forEach>
        </display:column>--%>
    </display:table>
    </div>
</fieldset>

    <c:if test="${actionBean.hakmilik ne null}">
          <fieldset class="aras1">
              <legend>Tuan Tanah</legend><br />
              <div align="center">
              <display:table class="tablecloth" name="${actionBean.senaraiPerbicaraanKehadiran}" cellpadding="0" cellspacing="0" id="tbl1">
                    <display:column title="No" sortable="true" >${tbl1_rowNum}</display:column>
                    <display:column title="Orang Berkepentingan" style="vertical-align:baseline">
                        <table border="0">
                            <tr>
                                <td>
                                    <s:link beanclass="etanah.view.stripes.pengambilan.SamanPemulaSek8ActionBean"
                                            event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                        <s:param name="disable" value="${actionBean.disable}"/>
                                        <s:param name="idPihak" value="${tbl1.pihak.pihak.idPihak}"/>
                                        <s:param name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>
                                        <c:out value="${tbl1.pihak.pihak.nama}"/>
                                    </s:link>
                                </td>
                                <td>(<c:out value="${tbl1.pihak.syerPembilang}/${tbl1.pihak.syerPenyebut}"/>)</td>
                            </tr>
                        </table>
                    </display:column>
              </display:table>
              </div>
                <br /><br />
          </fieldset><br />
      </c:if>

<c:if test="${showDetails}">
    <div class="subtitle">
        <fieldset class="aras1" id="locate">
            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr align="left">
                        <td align="center">
                            <%--<b><u>DEPOSIT DALAM MAHKAMAH</u></b>--%>
                        </td>
                    </tr>
                    <tr align="left">
                        <td align="center">
                            <b><u>DALAM MAHKAMAH TINGGI MALAYA DI MELAKA</u></b>
                        </td>
                    </tr>
                    <tr><td> &nbsp;</td></tr>
                    <tr align="left">
                        <td align="left">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;SAMAN PEMULA NO:
                            <c:if test="${actionBean.disable eq 'FALSE'}"><s:text name="samanPemulaBil" size="16" /></c:if>
                            <c:if test="${actionBean.disable eq 'TRUE'}"><s:text name="samanPemulaBil" size="16" disabled="true"/></c:if><hr></td>
                    </tr>
                    <tr><td> &nbsp;</td></tr>
                    <div class="content" align="center">
                        <tr align="left"><td align="center"><br />DALAM PERKARA <font style="text-transform: uppercase">${actionBean.heading}</font><s:hidden name="heading"/></td></tr><br />
                        <tr align="left"><td align="center">DAN</td></tr>
                        <tr align="left"><td align="center"><br />DALAM PERKARA AKTA PENGAMBILAN TANAH NO.34, 1960</td></tr>
                        <tr align="left"><td align="center">DAN</td></tr>
                        <tr align="left"><td align="center"><br />DALAM PERKARA ATURAN 7 KAEDAH 2(1) KAEDAH-KAEDAH MAHKAMAH TINGGI TAHUN 1980</td></tr>
                    </div>

                        <tr align="left"><td align="center"><br />ANTARA</td></tr>
                        <tr align="center"><td><br />PENTADBIR TANAH <font style="text-transform: uppercase">${actionBean.hakmilik.daerah.nama}</font>, MELAKA - Pemohon</td></tr>

                    <tr align="center"><td><br /><br /><b><u>SAMAN PEMULA</u></b></td></tr>
                    <tr><td> &nbsp;</td></tr>

                </table>
            </div>
            <div class="content" align="center" >
                <table border="0" width="50%" align="center">
                    <tr><td>Benarkan semua pihak hadir di hadapan Timbalan Pendaftar / Penolong Kanan Pendaftar</td></tr>
                    <tr><td>dalam kamar pada
                            <c:if test="${actionBean.disable eq 'FALSE'}"><s:text name="tarikhSaman" class="datepicker" id="tarikhSaman" size="12" formatPattern="dd/MM/yyyy"/></c:if>
                            <c:if test="${actionBean.disable eq 'TRUE'}"><s:text name="tarikhSaman" class="datepicker" id="tarikhSaman" size="12" formatPattern="dd/MM/yyyy" disabled="true"/></c:if>
                            Jam
                             <c:if test="${actionBean.disable eq 'FALSE'}">
                                 <s:select name="jam" style="width:60px" id="jam">
                                      <s:option>Jam</s:option>
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
                                  </s:select>
                                  <s:select name="minit" style="width:62px" id="minit">
                                      <s:option>Minit</s:option>
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
                                  <s:select name="pagiPetang" style="width:80px" id="pagiPetang">
                                      <s:option>Pilih</s:option>
                                      <s:option value="PAGI">Pagi</s:option>
                                      <s:option value="PETANG">Petang</s:option>
                                  </s:select></c:if>
                            <c:if test="${actionBean.disable eq 'TRUE'}"><s:select name="jam" style="width:60px" id="jam" disabled="true">
                                      <s:option>Jam</s:option>
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
                                  </s:select>
                                  <s:select name="minit" style="width:62px" id="minit" disabled="true">
                                      <s:option>Minit</s:option>
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
                                  <s:select name="pagiPetang" style="width:80px" id="pagiPetang" disabled="true">
                                      <s:option>Pilih</s:option>
                                      <s:option value="PAGI">Pagi</s:option>
                                      <s:option value="PETANG">Petang</s:option>
                                  </s:select></c:if>
                                   bagi pendengaran</td></tr>
                                   <c:set value="${actionBean.permohonanPihak.syerPembilang}" var="a"/>
                                   <c:set value="${actionBean.permohonanPihak.syerPenyebut}" var="b"/>
                                   <c:set value="${actionBean.hakmilikPermohonan.luasTerlibat}" var="c"/>
                                   <c:set value="${(actionBean.hakmilikPerbicaraan.keputusanNilai)/(actionBean.hakmilikPermohonan.luasTerlibat)}" var="d"/>
                                   <c:set value="${a/b*c}" var="e"/>
                                   <c:set value="${e*d}" var="f"/>
                                   <c:forEach items="${actionBean.senaraiPerbicaraanKehadiran}" var="list">
                                       <c:if test="${actionBean.permohonanPihak.pihak.idPihak == list.pihak.pihak.idPihak}">
                                           <c:set var="B" value="0"/>
                                           <c:forEach items="${list.senaraiPenilaian}" var="list1">
                                               <c:set value="${B + list1.amaun}" var="B"/>
                                           </c:forEach>
                                           <c:set value="${B}" var="g"/>
                                       </c:if>
                                   </c:forEach>
                    <tr><td>permohonan pihak pemohon <%--${actionBean.hakmilik.daerah.nama}--%>untuk mengeluarkan wang sebanyak RM <fmt:formatNumber pattern="#,##0.00" value="${f+g}"/></td></tr>
                    <tr><td>sebagai sebahagian daripada amaun yang telah didepositkan melalui Perintah Mahkamah</td></tr>
                    <tr><td>bertarikh
                            <c:if test="${actionBean.disable eq 'FALSE'}"><s:text name="tarikhTerimaPerintah" class="datepicker" id="tarikhTerimaPerintah" size="12" formatPattern="dd/MM/yyyy"/></c:if>
                            <c:if test="${actionBean.disable eq 'TRUE'}"><s:text name="tarikhTerimaPerintah" class="datepicker" id="tarikhTerimaPerintah" size="12" formatPattern="dd/MM/yyyy" disabled="true"/></c:if>
                            perkara tersebut di atas.</td></tr>
                    <tr><td>&emsp;</td></tr>
                    <tr><td>&emsp;&emsp;&emsp;Bertarikh pada
                            <c:if test="${actionBean.disable eq 'FALSE'}"><s:text name="tarikhIkrar" class="datepicker" id="tarikhIkrar" size="12" formatPattern="dd/MM/yyyy"/></c:if>
                            <c:if test="${actionBean.disable eq 'TRUE'}"><s:text name="tarikhIkrar" class="datepicker" id="tarikhIkrar" size="12" formatPattern="dd/MM/yyyy" disabled="true"/></c:if>
                            .</td></tr>
                </table>
            </div>
        </fieldset>
    </div>
                    <p align="center">
                        <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                        <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                        <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
                        <c:if test="${actionBean.disable eq 'FALSE'}">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                        </c:if>
                    </p>
        </c:if>
    <%--</c:if>--%>
  </div>
</s:form>
