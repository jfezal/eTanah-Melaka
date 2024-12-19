<%--
    Document   : Deraf_Perintah_Sblm
    Created on : Apr 18, 2011, 10:01:23 AM
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

function ajaxLink(link, update) {
$.get(link, function(data) {
$(update).html(data);
$(update).show();
});
return false;
}
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.PerintahSblmActionBean">
<div  id="hakmilik_details">
<s:messages/>
<s:errors/>
<s:hidden name="show" value="${actionBean.show}"/>

<fieldset class="aras1"><br/>
    <div align="center">
    <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}" pagesize="5" cellpadding="0" cellspacing="0"
                   requestURI="/pengambilan/perintah_sblm" id="line">
        <display:column title="No" sortable="true">${line_rowNum}</display:column>
        <display:column property="hakmilik.idHakmilik" title="Id Hakmilik"/>
        <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}&nbsp;${line.hakmilik.noLot}</display:column>
        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
        <display:column title="Tuan Tanah" >
                        <c:set value="1" var="count"/>
                        <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                             <table border="0">
                              <tr>
                                 <td><c:out value="${count}"/>)</td>
                                 <td>
                                    <s:link beanclass="etanah.view.stripes.pengambilan.PerintahSblmActionBean"
                                            event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                        <s:param name="show" value="${actionBean.show}"/>
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
         </display:column>
    </display:table>
    </div>
</fieldset>
<br/><br/>
      <c:if test="${actionBean.permohonanPihak ne null}">
          <fieldset class="aras1">
              <div class="content" align="left">
                  <table align="center"  width="70%" border="0">
                      <tr>
                          <td width="70" nowrap rowspan="0"><label >Nama Penolong Kanan Pendaftar Mahkamah Tinggi :</label></td>
                          <td >
                              <c:if test="${showEdit}"> <s:text name="namaPenolongKananPendaftar"/> </c:if>
                              <c:if test="${showDetails}"> ${actionBean.namaPenolongKananPendaftar}<s:hidden name="namaPenolongKananPendaftar"/> </c:if>
                          </td>
                      </tr>
                      <tr>
                          <td ><label>Saman Pemula Bil :</label></td>
                          <td >
                              <c:if test="${showEdit}"> <s:text name="samanPemulaBil"/> </c:if>
                              <c:if test="${showDetails}"> ${actionBean.samanPemulaBil} <s:hidden name="samanPemulaBil"/></c:if>
                          </td>
                      </tr>
                      <tr>
                          <td ><label>Tarikh Saman Pemula/Saman Dalam Kamar :</label></td>
                          <td >
                              <c:if test="${showEdit}"> <s:text name="tarikhSaman" class="datepicker" id="tarikhSaman" size="12" formatPattern="dd/MM/yyyy"/> </c:if>
                              <c:if test="${showDetails}">
                                  ${actionBean.tarikhSaman}
                                  <s:hidden name="tarikhSaman" class="datepicker" id="tarikhSaman" formatPattern="dd/MM/yyyy"/>
                              </c:if>
                          </td>
                      </tr>
                      <tr>
                          <td ><label>Masa Saman Pemula/Saman Dalam Kamar :</label></td>
                          <td >
                              <c:if test="${showEdit}">
                                  <s:select name="jam" style="width:60px" id="jam">
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
                                  </s:select>
                                  <s:select name="minit" style="width:62px" id="minit">
                                      <s:option value="00">Minit</s:option>
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
                                      <s:option value="0">Pilih</s:option>
                                      <s:option value="PAGI">Pagi</s:option>
                                      <s:option value="PETANG">Petang</s:option>
                                  </s:select>
                              </c:if>
                              <c:if test="${showDetails}">
                                  ${actionBean.jam}:${actionBean.minit} ${actionBean.pagiPetang}
                                  <s:hidden name="jam"/><s:hidden name="minit"/><s:hidden name="pagiPetang"/>
                              </c:if>
                          </td>
                      </tr>
                      <tr>
                          <td ><label>Lokasi Saman Pemula/Saman Dalam Kamar :</label></td>
                          <td >
                              <c:if test="${showEdit}"> <s:text name="lokasiSaman" /></c:if>
                              <c:if test="${showDetails}"> ${actionBean.lokasiSaman} <s:hidden name="lokasiSaman" /></c:if>
                          </td>
                      </tr>
                      <tr>
                          <td ><label>Tarikh Ikrar :</label></td>
                          <td >
                              <c:if test="${showEdit}"> <s:text name="tarikhIkrar" class="datepicker" id="tarikhIkrar" size="12" formatPattern="dd/MM/yyyy"/></c:if>
                              <c:if test="${showDetails}">
                                  <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.tarikhIkrar}"/>
                                  <s:hidden name="tarikhIkrar" class="datepicker" id="tarikhIkrar"  formatPattern="dd/MM/yyyy"/>
                              </c:if>
                          </td>
                      </tr>
                      <tr>
                          <td >
                              <c:if test="${showDetails}">
                                  <label>Tarikh Terima Perintah Dari Mahkamah :</label>
                              </c:if>
                          </td>
                          <td >
                              <c:if test="${showEdit}"> <s:hidden name="tarikhTerimaPerintah" class="datepicker" id="tarikhTerimaPerintah" formatPattern="dd/MM/yyyy"/></c:if>
                              <c:if test="${showDetails}">
                                  <s:text name="tarikhTerimaPerintah" class="datepicker" id="tarikhTerimaPerintah" size="12" formatPattern="dd/MM/yyyy"/>
                              </c:if>
                          </td>
                      </tr>
                  </table>

              </div>
          </fieldset><br/>

              <div align="center">
                  <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                  <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                  <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
              </div>
      <br />
       </c:if>

</div>
</s:form>