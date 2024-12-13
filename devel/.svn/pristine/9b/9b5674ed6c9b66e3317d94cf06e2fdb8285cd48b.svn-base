<%--
    Document   : add_urusan
    Created on : Feb 23, 2010, 2:05:49 PM
    Author     : wan.fairul
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

    $('#id_serah').hide();

    $('form').submit(function() {
      doBlockUI();
      var valid = false;
      $('.kodUrusan').each(function(index) {
        updateSelect(index);
        var val = $('#kodUrusanKod' + index).val();
        if (val != '') {
          valid = true;
        }
        if (val == '') {
          valid = false;
        }
      });
      doUnBlockUI();
      return valid;
    });
  });

  function updateSelect(idx) {
    var textKodUrusanKod = document.getElementById('kodUrusanKod' + idx);
    if (textKodUrusanKod.value.length > 0) {
      var selectKodUrusan = document.getElementById('kodUrusan' + idx);
      selectKodUrusan.selectedIndex = 0;
      var kod = textKodUrusanKod.value.toUpperCase();
      for (var i = 0; i < selectKodUrusan.options.length; ++i) {
        if (selectKodUrusan.options[i].value == kod) {
          selectKodUrusan.selectedIndex = i;
          updateJabatan(idx, selectKodUrusan.options[i].parentNode.label);
          break;
        }
      }
      if (selectKodUrusan.selectedIndex == 0) {
        $('#kodUrusanKod' + idx).val('');
        alert('Kod Urusan ' + kod + ' tidak dijumpai.');
        $('#kodUrusanKod' + idx).focus();
      }
    }
  }

  function updateKod(i) {
    var selectKodUrusan = document.getElementById('kodUrusan' + i);
    if (selectKodUrusan.selectedIndex > 0) {
      var textKodUrusanKod = document.getElementById('kodUrusanKod' + i);
      textKodUrusanKod.value = selectKodUrusan.options[selectKodUrusan.selectedIndex].value;
      updateJabatan(i, selectKodUrusan.options[selectKodUrusan.selectedIndex].parentNode.label);
    }
  }

  function updateJabatan(whichItem, namaJabatan) {
    var selectJabatan = document.getElementById('kodJabatan' + whichItem);
    for (i = 0; i < selectJabatan.length; i++) {
      if (selectJabatan.options[i].text == namaJabatan) {
        selectJabatan.selectedIndex = i;
        break;
      }
    }
  }

  function selectUrusanForJabatan(whichItem) {
    var kodJabatan = $("#kodJabatan" + whichItem + " option:selected").text();

    var found = false;
    var selectUrusan = document.getElementById("kodUrusan" + whichItem);
    for (i = 0; i < selectUrusan.length; i++) {
      if (selectUrusan.options[i].parentNode.label == kodJabatan) {
        selectUrusan.selectedIndex = i;
        found = true;
        break;
      }
    }

    if (!found)
      selectUrusan.selectedIndex = 0;
  }


</script>
<script language="javascript">
  function save(event, f, idPermohonan)  {
    doBlockUI();
    var q = $(f).formSerialize();
    var url = f.action + '?' + event + '&idPermohonan=' + idPermohonan;
    $.post(url, q,
            function(data) {
              $('#page_div', opener.document).html(data);
              self.close();
            }, 'html');
  }

  function changev(value) {
    if (value == "y")    {
      $('#id_serah').show();
    }
    if (value == "n")    {
      $('#id_serah').hide();
    }
  }

  function doBlockUI() {
    $.blockUI({
      message: $('#displayBox'),
      css: {
        top: ($(window).height() - 50) / 2 + 'px',
        left: ($(window).width() - 50) / 2 + 'px',
        width: '50px'
      }
    });
  }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">
  <s:hidden name="idPermohonan" value="${actionBean.idPermohonan}"/>
  <s:messages />
  <s:errors />
  <div class="subtitle">
    <fieldset class="aras1">
      <legend>
        Maklumat Tambah Urusan
      </legend>
      <p style="color:red">
        *Isi ruang pembetulan kemudian tekan butang simpan.<br/>
      </p>
      <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 1}">
        <p>
          <label>&nbsp;</label>
          <font color="red" size="2">
            <input type="checkbox" name="copyToAll" value="1"/>
            <em>Sila klik jika sama untuk semua hakmilik.</em>
          </font>
        </p>
      </c:if>
      <br>
      <c:set scope="request" var="senaraiJabatan" value="${listUtil.senaraiKodJabatan}" />
      <c:set scope="request" var="senaraiUrusanPendaftaran" value="${actionBean.senaraiUrusanPendaftaran}" />

      <p><label for="kodUrusankod"><em>*</em>Urusan</label><nobr>
        <s:text name="kodUrusan" id="kodUrusanKod0" onkeyup="this.value=this.value.toUpperCase();" onblur="javascript:updateSelect(0);" size="6" class="kodUrusan"/>
        <s:select name="senaraiUrusan[0].kodUrusanPilih" id="kodUrusan0" onchange="javascript:updateKod(0);"
                  style="width:400px;" >
          <s:option label="Pilih Urusan..."  value="0" />
          <c:forEach items="${senaraiUrusanPendaftaran}" var="i" >
            <s:option value="${i.kod}" >${i.kod} - ${i.nama}</s:option>
          </c:forEach>
        </s:select>
        </p>
        <p>
          <label><em>*</em>ID Perserahan :</label>
          <s:radio name="id" value="y" onchange="javaScript:changev(this.value)"></s:radio> Ada
          <s:radio name="id" value="n" onchange="javaScript:changev(this.value)"></s:radio> Tiada
          </p>
          <div id="id_serah" class="subtitle">
            <p>
              <label>&nbsp;</label>
            <s:text name="idPerserahan"/>&nbsp;
          </p>
        </div>
        <p>
          <label><em>*</em>ID Hakmilik :</label> 
          <s:select name="idHakmilik">
            <c:forEach items="${actionBean.hakmilikPermohonanList}" var="item" varStatus="line">
              <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
              </s:option>
            </c:forEach>
          </s:select>
        </p>
        <p>
          <label for="noFail">No. Rujukan Fail:</label>
          <s:text name="noFail" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
        <p>
          <label>No Jilid :</label><s:text name="noJilid" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
        </p>
        <p>
          <label>No Folio :</label><s:text name="noFolio" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
        </p>
        <p>
          <label><em>*</em>Tarikh Daftar :</label><s:text name="tarikhDaftarUrusan" id="datepicker" class="datepicker"/>&nbsp;
        </p>
        <p>
          <label><font color="red">*</font>Masa :</label>
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
        </p>  
        <br><br>
        <table style="margin-left: auto; margin-right: auto;">
          <tr>
            <td>&nbsp;</td>
            <td><div>
                <s:button name="saveTambahUrusan" value="Simpan" class="btn" onclick="save(this.name, this.form, '${actionBean.idPermohonan}')"/>
                <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>

              </div>
            </td>
          </tr>
        </table>
        <br/>

    </fieldset>
  </div>

</s:form>


