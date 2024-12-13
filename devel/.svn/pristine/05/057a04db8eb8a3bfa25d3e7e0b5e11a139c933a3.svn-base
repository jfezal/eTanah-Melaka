<%--
    Document   : update_urusan
    Created on : Feb 23, 2010, 2:05:49 PM
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
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

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
  
   function completeIdPerserahan(id,type){ 
                var caw = document.getElementById("caw");
                var res = id.split("/");
                var seq = res[0].length;
                
                if((id.length <16)&&(id.length >0)){
                    var b='';
                    var jenis='';
                    switch(seq){
                        case 1 : 
                            b='00000'+res[0];
                            break;
                        case 2 : 
                            b='0000'+res[0];
                            break;
                        case 3 : 
                            b='000'+res[0];
                            break;
                        case 4 : 
                            b='00'+res[0];
                            break;
                        case 5 : 
                            b='0'+res[0];
                            break;
                        case 6 : 
                            b=res[0];
                            break;
                    }                   
                   
                    
                    var idSerah = '04'+caw.value+type+res[1]+b;
                    if(type == 'SA'){
//                        jenis = '#SAB';
                        if($('input[name=allowed_2]').is(':checked')){                         
                        }

                        else if($('input[name=allowed_2]').is(':unchecked')){     
                           $('#SAB').val(idSerah);
                        }
                    }
                        
                    if(type == 'SB'){
//                        jenis = '#SBD';
                        if($('input[name=allowed_3]').is(':checked')){                         
                        }

                        else if($('input[name=allowed_3]').is(':unchecked')){     
                           $('#SBD').val(idSerah);
                        } 
                    }
                        
                    if(type == 'SW'){
//                        jenis = '#SWD';
                        if($('input[name=allowed_1]').is(':checked')){                         
                        }

                        else if($('input[name=allowed_1]').is(':unchecked')){     
                           $('#SWD').val(idSerah);
                        } 
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
  function save(event, f, idPembetulan) {
    doBlockUI();
    var q = $(f).formSerialize();
    var url = f.action + '?' + event + '&idPembetulan=' + idPembetulan;
    $.post(url, q,
            function(data) {
              $('#page_div', opener.document).html(data);
              self.close();
            }, 'html');
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
  <s:hidden name="idPembetulan" value="${actionBean.idPembetulan}"/>
  <s:hidden name="caw" id="caw" value="${actionBean.caw}"/>


  <s:messages />
  <s:errors />

  <div class="subtitle">

    <fieldset class="aras1">
      <legend>
        Kemaskini Maklumat Tambah Urusan
      </legend>
      <p style="color:red">
        *Isi ruang pembetulan kemudian tekan butang simpan.<br/>

      </p>
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
      </nobr>
      </p>
      <p>
        <label><em>*</em>ID Perserahan :</label> 
        <s:text name="idPerserahan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>&nbsp;
      </p>
      <p>
        <label><em>*</em>ID Hakmilik :</label> <s:select name="idHakmilik">
          <c:forEach items="${actionBean.hakmilikPermohonanList}" var="item" varStatus="line">
            <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
              ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
            </s:option>
          </c:forEach>
        </s:select>
      </p>
      <p>
        <label>No Jilid :</label>
        <s:text name="noJilid" onkeyup="this.value=this.value.toUpperCase();" size="35"/>&nbsp;
      </p>
      <p>
        <label>No Folio :</label>
        <s:text name="noFolio" onkeyup="this.value=this.value.toUpperCase();" size="35"/>&nbsp;
      </p>
      <p>
        <label for="tarikhDaftar">Tarikh daftar :</label>
        <s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonanPembetulanUrusan.tarikhDaftar}"/>
        Masa: 
        <s:format formatPattern="hh:mm:ss aa" value="${actionBean.permohonanPembetulanUrusan.tarikhDaftar}"/>
      </p>
      <p>
        <label><em>*</em>Tarikh Daftar :</label>
        <s:text formatPattern="dd/MM/yyyy"  name="tarikhDaftar" id="datepicker" class="datepicker" size="35"/>&nbsp;
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
      <%--<p>
        <label>&nbsp;</label>
        <s:button name="updateTambahUrusan" value="Simpan" class="btn" onclick="save(this.name, this.form, '${actionBean.idPembetulan}')"/>
        <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>
      </p>--%>
      <br/>
    </fieldset>
  </div>

  <fieldset class="aras1">
    <legend>
      Maklumat Tambahan
    </legend>
    <br>
    <p>
      <label for="noFail">No. Rujukan Fail:</label>
      <s:text name="noFail" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
    </p>
    <p>
        <label>Nombor Surat Kuasa Wakil :</label>
        <s:text name="SWD" onchange="this.value=this.value.toUpperCase();"  onblur="completeIdPerserahan(this.value,'SW')" size="35" id="SWD"/>
        <font size ="1" color="red"> [Contoh Format:  12/2014]</font>    
        <font color="black">/</font>
        <s:checkbox name="allowed_1" id="allowed_1" onclick="allow();"/>
        <font color="black">Guna Format SPTB</font><font size ="1" color="red"> [Contoh:  Jil 33 Fol 68]</font>        
    </p>
    <p>
        <label>Nombor Surat Amanah :</label>
        <s:text name="SAB" onchange="this.value=this.value.toUpperCase();"  onblur="completeIdPerserahan(this.value,'SA')" size="35" id="SAB"/>
        <font size ="1" color="red"> [Contoh Format:  12/2014]</font>    
        <font color="black">/</font>
        <s:checkbox name="allowed_2" id="allowed_2" onclick="allow();"/>
        <font color="black">Guna Format SPTB</font><font size ="1" color="red"> [Contoh:  Jil 33 Fol 68]</font> 
    </p>
    <p>
        <label>Nombor Surat Kebenaran :</label>
        <s:text name="SBD" onchange="this.value=this.value.toUpperCase();"  onblur="completeIdPerserahan(this.value,'SB')" size="35" id="SBD"/>
        <font size ="1" color="red"> [Contoh Format:  12/2014]</font>    
        <font color="black">/</font>
        <s:checkbox name="allowed_3" id="allowed_3" onclick="allow();"/>
        <font color="black">Guna Format SPTB</font><font size ="1" color="red"> [Contoh:  Jil 33 Fol 68]</font> 
    </p>
    <c:if test="${actionBean.kodUrusan ne 'HLTE'}">
      <p>
        <label>Nombor Warta :</label>
        <s:text name="noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
      </p>
      <p>
        <label>Tarikh Warta :</label>
        <s:text name="tarikhRujukan" id="tarikhRujukan" class="datepicker" size="35"/>
      </p>
      <p>
        <label>Kawasan :</label>
        <s:text name="kawasan" id="kawasan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
      </p>
    </c:if>
    <br>
    
      <legend>
           <c:if test="${actionBean.kodUrusan eq 'HLTE'}">Maklumat Berkaitan Hakmilik</c:if>
           <c:if test="${actionBean.kodUrusan ne 'HLTE'}">Maklumat Berkaitan Pajakan</c:if>
               
      </legend>
      <div class="content" align="center">
        <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="sizeHakmilikPermohonan"/>
        <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" style="width:80%;"  cellpadding="0" cellspacing="0" id="line">
          <display:column title="No" sortable="true">${line_rowNum}</display:column>
          <display:column title="ID Hakmilik">${line.hakmilik.idHakmilik}
            <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.hakmilik.idHakmilik}"/>
          </display:column>
          <display:column title="Luas / Unit Asal">
            ${line.hakmilik.luas}
            ${line.hakmilik.kodUnitLuas.nama}
          </display:column>
          <display:column title="Luas / Unit Terlibat" ><%--strKodUOM[${line_rowNum-1}]"--%>
            <s:text name="luasTerlibat" formatPattern="###0.0000" size="10"/>
            <s:select  style="text-transform:uppercase"  name="strKodUOM">
              <s:option value=" ${line.kodUnitLuas.kod}"> 
                <c:if test="${line.kodUnitLuas.kod ne null}">
                  ${line.kodUnitLuas.nama}
                </c:if>
                <c:if test="${line.kodUnitLuas.kod eq null}">
                  -- SILA PILIH --
                </c:if>
              </s:option>
              <s:options-collection collection="${list.senaraiKodUOMByJarak}" label="nama" value="kod"/>
            </s:select>
          </display:column>
          
          <display:column title="Tempoh Pajakan">
            <s:text name="tempohTahun"  size="2"/> Tahun            
            <s:text name="tempohBulan" size="2"/> Bulan    
            <s:text name="tempohHari" size="2"/> Hari 
          </display:column>  
          <display:column title="Tarikh Pajakan">
            Tarikh Kuat Kuasa : <s:text name="trhKuasa" id="trKuasa" class="datepicker" size="10"/><br><br> 
            Tarikh Tamat &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: <s:text name="trhTamat" id="trhTamat" class="datepicker" size="10"/>                 
          </display:column>          
                 
        </display:table>
      </div>
    
    <div class="content" align="center">
      <c:if test="${actionBean.kodUrusan eq 'ABT-D'}">
        <legend>
          Maklumat Berkaitan Hakmilik
        </legend>
        <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" />
        <display:table class="tablecloth"  name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
          <display:column title="No" sortable="true">${line_rowNum}</display:column>
          <display:column title="Luas / Unit Asal">
            ${line.hakmilik.luas}
            ${line.hakmilik.kodUnitLuas.nama}
          </display:column>
          <display:column title="Luas / Unit Terlibat" ><%--strKodUOM[${line_rowNum-1}]"--%>
            <s:text name="luasTerlibat" formatPattern="###0.0000" size="10"/>
            <s:select  style="text-transform:uppercase"  name="strKodUOM">
              <s:option value=" ${line.kodUnitLuas.kod}"> ${line.kodUnitLuas.nama}</s:option>
              <s:options-collection collection="${list.senaraiKodUOMByJarak}" label="nama" value="kod"/>
            </s:select>
          </display:column>
          <display:column title="Cukai">
            RM ${line.hakmilik.cukai}
          </display:column>
        </display:table>
      </c:if>
      <c:if test="${actionBean.kodUrusan eq 'ABTKB'
                    or actionBean.kodUrusan eq 'ABTBH'
                    or actionBean.kodUrusan eq 'ABT'
                    or actionBean.kodUrusan eq 'ABT-A'}">
            <legend>
              Maklumat Berkaitan Hakmilik
            </legend>
            <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" />
            <display:table class="tablecloth"  name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
              <display:column title="No" sortable="true">${line_rowNum}</display:column>
              <display:column title="Luas / Unit Asal">
                ${line.hakmilik.luas}
                ${line.hakmilik.kodUnitLuas.nama}
              </display:column>
              <display:column title="Luas / Unit Terlibat" ><%--strKodUOM[${line_rowNum-1}]"--%>
                <s:text name="luasTerlibat" formatPattern="###0.0000" size="10"/>
                <s:select  style="text-transform:uppercase"  name="strKodUOM">
                  <s:option value=" ${line.kodUnitLuas.kod}"> ${line.kodUnitLuas.nama}</s:option>
                  <s:options-collection collection="${list.senaraiKodUOMByJarak}" label="nama" value="kod"/>
                </s:select>
              </display:column>
              <display:column title="Cukai">
                RM ${line.hakmilik.cukai}
              </display:column>
              <display:column title="Cukai Baru">
                RM <s:text name="cukaiBaru" formatPattern="###0"/>
              </display:column>
            </display:table>
      </c:if>
      <c:if test="${actionBean.kodUrusan eq 'GD'}">
      </c:if>    
      <br>
      <p>
        <s:button name="updateTambahUrusan" value="Simpan" class="btn" onclick="save(this.name, this.form, '${actionBean.idPembetulan}')"/>
        <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>
      </p>
    </div>
  </fieldset>
</s:form>


