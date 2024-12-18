<%-- 
    Document   : pertanyaan_hakmilik
    Created on : Apr 9, 2010, 12:00:06 PM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html>
  <head><title>Pertanyaan Hakmilik</title>
    <link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
    <script type="text/javascript">
      $(document).ready(function() {
        dialogInit('Carian Hakmilik');
      <%--       var pguna = ${actionBean.pengguna.idPengguna};
             alert(pguna);--%>
                 var negeri = "${actionBean.kodNegeri}";
                 if (negeri == "melaka") {
                   $('#akaun').focus();
                 } else {
                   $('#hakmilik').focus();
                 }
                 $('#search').click(function() {
                   doBlockUI();
                 });
               });
               function zeroPad(num, count)
               {
                 var numZeropad = num + '';
                 while (numZeropad.length < count) {
                   numZeropad = "0" + numZeropad;
                 }
                 return numZeropad;
                 $("#noLot").val(numZeropad);
               }
               function change() {
                 var a = $("#kod").val();
                 var b = a.toUpperCase();

                 $("#kod").val(b);
               }

               //                       function filterDaerah(kodDaerah){
               //                           var url = '${pageContext.request.contextPath}/common/carian_hakmilik?penyukatanBPM&daerah='+kodDaerah;
               //                           $.get(url,
               //                           function(data){
               //                                $('#page_div').html(data);
               //                           },'html');
               //                       }
               function filterDaerah(kodDaerah, frm) {
                 //                            var jabatan = f.elements['pguna.kodJabatan.kod'].value ;
                 var url = '${pageContext.request.contextPath}/common/carian_hakmilik?penyukatanBPM&daerah=' + kodDaerah;
                 frm.action = url;
                 frm.submit();

               }
               function filterBPM(kodBPM, frm) {
                 var daerah = $('#daerah').val();
                 var url = '${pageContext.request.contextPath}/common/carian_hakmilik?penyukatanSeksyen&bandarPekanMukim=' + kodBPM + '&daerah' + daerah;
                 frm.action = url;
                 frm.submit();
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
    <script type="text/javascript">
      function popup(id) {
        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?infoPembayar&idPegang=" + id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
      }


      function refresh1(v) {
        var url = '${pageContext.request.contextPath}/common/carian_hakmilik?papar&idHakmilik=' + v;
        $.get(url,
                function(data) {
                  $('#daerah').html(data);
                }, 'html');
      }

      function doSubmit(e, f, g, h) {
        //alert('test');
        //var q = $('#carian_hakmilik').formSerialize();
        //alert(h);
        //alert(e);
        //alert(g);
        //while (h.parentNode && h.parentNode.tagName != "FORM"){
        //    h = h.parentNode;
        //}
        //var f = $('#carian_hakmilik').
        //svar f = h.parentNode;
        var q = $(f).formSerialize();
        //var q = $('#carian_hakmilik').serialize();
        f.action = f.action + '?' + e + '&idHakmilik=' + g + '&noAkaun=' + h + '&popup&' + q;
        //alert(f.action.toString());
        f.submit();
      }

      function completeId(id) {
        var l = id.length;
        if (l > 0) {
          var lengthId = 8;
          var i = "";
          for (var x = 0; x < (lengthId - l); x++) {
            i = i + '0';
          }
          var noHakmilik = i + id;
          $("#noHakmilik").val(noHakmilik);
        }
      }


    </script>
  </head>
  <body>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
    <s:form beanclass="etanah.view.stripes.common.CarianHakmilik" id="carian_hakmilik">
      <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
      <s:hidden name="tukarGanti" id="tukarGanti"/>
      <div class="subtitle">
        <s:errors/>
        <s:messages/>

        <fieldset class="aras1">
          <legend>
            Pertanyaan Hakmilik
          </legend>
          <div class="instr-fieldset">
            <font color="red">PERINGATAN:</font>Sila Masukan Maklumat Berikut.
          </div>&nbsp;
          <c:if test="${actionBean.kodNegeri eq 'melaka'}">
            <p>
              <label> No Akaun :</label>
              <s:text id="akaun" name="noAkaun" maxlength="20" size="31" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;<font size="1" color="red"> (cth : 0300019808)</font>
            </p>
          </c:if>
          <p>
            <label >ID Hakmilik :</label>
            <s:text id="hakmilik" name="idHakmilik"  size="31" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;<font size="1" color="red"> (cth : 050505GRN00230830)</font>
          </p>
          <p>
            <label>  Daerah :</label>
            <s:select name="daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value,this.form);">
              <s:option value="">--Sila Pilih--</s:option>
              <%--<s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"  />--%>
              <c:forEach items="${listUtil.senaraiKodDaerah}" var="i" >
                <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
              </c:forEach>
            </s:select>
          </p>

          <p>
            <label>  Bandar/Pekan/Mukim :</label>
            <s:select name="bandarPekanMukim" id="bandarPekanMukim" style="width:210px;" onchange="filterBPM(this.value,this.form);">
              <s:option value="">--Sila Pilih--</s:option>
              <%--<s:options-collection collection="${actionBean.senaraiBPM}" label="nama" value="kod" sort="nama" />--%>
              <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
              </c:forEach>
            </s:select>
          </p>
          <p>
            <label>  Seksyen :</label>
            <s:select name="seksyen" id="seksyen" style="width:210px;">
              <s:option value="">--Sila Pilih--</s:option>
              <c:forEach items="${actionBean.senaraiKodSeksyen}" var="i" >
                <s:option value="${i.kod}">${i.nama}</s:option>
              </c:forEach>
            </s:select>
          </p>
          <p>
            <label>  Status Hakmilik :</label>
            <s:select name="kodStatusHakmilik" style="width:210px;">
              <s:option value="">--Sila Pilih--</s:option>
              <s:options-collection collection="${listUtil.senaraiKodStatusHakmilik}" label="nama" value="kod" sort="nama" />
            </s:select>
          </p>
          <p>
            <label >Jenis Hakmilik :</label>
            <s:text name="kodHakmilik" id="kod" size="31" onkeyup="javaScript:change();" />
            <%--<s:select name="kodHakmilik" style="width:210px;">
                <s:option value="">--Sila Pilih--</s:option>
                <s:options-collection collection="${listUtil.senaraiKodHakmilik}" label="kod" value="kod" sort="nama" />
            </s:select>--%>
          </p>
          <p>
            <label >No Hakmilik :</label>
            <s:text name="noHakmilik"  size="31" id="noHakmilik" maxlength="8" /><%-- onblur="completeId(this.value)"/>--%>&nbsp;<font size="1" color="red"> (cth : 00230830) </font>
          </p>
          <p>
            <label >Kod Lot :</label>
            <%--<s:select name="hakmilik.lot.kod" style="width:210px;">--%>
            <s:select name="lot" style="width:210px;">
              <s:option value="">--Sila Pilih--</s:option>
              <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
            </s:select>
          </p>
          <p>
            <label >No Lot/PT :</label>
            <s:text name="noLot" id="noLot"  maxlength="15" size="31"/><%-- onblur="zeroPad(this.value,7);"/>--%>
          </p>
          <p>
            <label> Nama Pembayar :</label>
            <s:text name="namaPembayar"  maxlength="50" size="31" onkeyup="this.value=this.value.toUpperCase();"/>
          </p>
          <p>
            <label >No Pengenalan Pembayar :</label>
            <s:text name="noPengenalanP"  maxlength="15" size="31" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;<font size="1" color="red"> (cth : 840913117626 / 840913-11-7626)</font>
          </p>
          <%--  <p>
                 <label >Jenis Pengenalan :</label>
                 <s:select name="pemegang.jenisPengenalan.nama" style="width:210px;">
                     <s:option value="">--Sila Pilih--</s:option>
                     <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                 </s:select>
             </p>--%>

          <%-- <p>
               <label> Nama Pemilik:</label>
               <s:text name="hpk.pihak.nama" maxlength="50" size="31" />
           </p>--%>
          <p>
            <label> Nama Pemilik :</label>
            <s:text name="namaPemilik" maxlength="50" size="31" onkeyup="this.value=this.value.toUpperCase();" />
          </p>
          <%-- <p>
               <label> No Pengenalan Pemilik:</label>
               <s:text name="hpk.pihak.noPengenalan"  maxlength="30" size="31" />&nbsp;<font size="1" color="red"> (cth : 840913117626)</font>
           </p>--%>
          <p>
            <label> No Pengenalan Pemilik :</label>
            <s:text name="noPengenalan"  maxlength="30" size="31" />&nbsp;<font size="1" color="red"> (cth : 840913117626 / 840913-11-7626)</font>
          </p>
          <p>
            <label> Kegunaan Tanah :</label>
            <s:text name="kegunaanTanah"  maxlength="30" size="31" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
          </p>
          <%--       <p>
                     <label >Jenis Pengenalan Pemilik :</label>
                     <s:select name="pihak.jenisPengenalan.nama" style="width:210px;">
                         <s:option value="">--Sila Pilih--</s:option>
                         <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                     </s:select>
                 </p>--%>
          <p>
          <label>&nbsp;</label>
            <s:submit name="search" value="Cari" class="btn" id="search"/>
            <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('carian_hakmilik');" />
          </p>
        </fieldset>
      </div>
      <c:if test="${actionBean.flag}">
        <br>
        <div class="subtitle">
          <fieldset class="aras1">
            <legend>
              Senarai Hakmilik
            </legend>

            <c:set var="row_num" value="${actionBean.__pg_start}"/>
            <div class="content" align="center">
              <%-- <display:table class="tablecloth" name="${actionBean.list}"
                             pagesize="10" cellpadding="0" cellspacing="0" requestURI="/common/carian_hakmilik" id="line">--%>
              <display:table class="tablecloth" name="${actionBean.listAkaun}"
                             pagesize="10" cellpadding="0" cellspacing="0"
                             requestURI="/common/carian_hakmilik" id="line"
                             sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                <c:set var="row_num" value="${row_num+1}"/>
                <%--  <display:column title="Pilih" sortable="true">
                      <div align="center"><s:radio name="idHakmilik" value="${line.hakmilik.idHakmilik}" /></div></display:column>
                --%>
                <display:column title="Bil" sortable="true" > <div align="center">${row_num}</div></display:column>

                <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                  <display:column property="noAkaun" title="No Akaun"  />
                </c:if>
                <%--<display:column  title="No Hakmilik"><a href="#" onclick="refresh1('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a></display:column>--%>
                <%-- <display:column  title="No Hakmilik"><a href="" onclick="doSubmit('papar',this,'${line.hakmilik.idHakmilik}')">${line.hakmilik.idHakmilik}</a></display:column>--%>
                <%--<display:column  title="No Hakmilik">${line.hakmilik.idHakmilik}</display:column>--%>
                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"  />
                <display:column property="hakmilik.bandarPekanMukim.nama" title="BandarPekanMukim"  />
                <display:column property="hakmilik.seksyen.nama" title="Seksyen"  />
                <display:column property="pemegang.nama" title="Nama Pembayar"  />
                <%--<display:column title="Nama Pembayar"><a href="#" onclick="popup('${line.pemegang.idPihak}');return false;">${line.pemegang.nama}</a></display:column>--%>
                <%--<display:column title="Nama Pembayar"><a href="#" onclick="popup('${line.pemegang.idPihak}');return false;">${line.pemegang.nama}</a></display:column>--%>
                <display:column title="Nama Pemilik Tanah (No Pengenalan)" >
                  <ol>                               
                    <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai" varStatus="a">
                      <c:if test="${(senarai.jenis.kod eq 'PM' or senarai.jenis.kod eq 'PA' or senarai.jenis.kod eq 'WAR' or
                                    senarai.jenis.kod eq 'ASL' or senarai.jenis.kod eq 'JA' or senarai.jenis.kod eq 'JK' or
                                    senarai.jenis.kod eq 'KL' or senarai.jenis.kod eq 'PDP' or senarai.jenis.kod eq 'PK' or
                                    senarai.jenis.kod eq 'PLK' or senarai.jenis.kod eq 'PP' or senarai.jenis.kod eq 'RP' or
                                    senarai.jenis.kod eq 'WKL' or senarai.jenis.kod eq 'WPA' or senarai.jenis.kod eq 'WS')
                                    and senarai.aktif eq 'Y'}">

                            <%--added by Tulasi--%>
                            <c:if test="${actionBean.noPengenalan eq null && actionBean.namaPemilik eq null}">
                              <li>
                                <c:out value="${senarai.pihak.nama}" />  <c:if test="${senarai.pihak.noPengenalan ne null}">(<c:out value="${senarai.pihak.noPengenalan}" />)</c:if>
                                </li>
                            </c:if>
                            <c:if test="${actionBean.noPengenalan ne null || actionBean.namaPemilik ne null}">
                              <c:if test="${actionBean.namaPemilik eq senarai.pihak.nama || actionBean.noPengenalan eq senarai.pihak.noPengenalan}">
                                <li>
                                  <c:out value="${senarai.pihak.nama}" /> <c:if test="${senarai.pihak.noPengenalan ne null}">(<c:out value="${senarai.pihak.noPengenalan}" />)</c:if>
                                  </li>
                              </c:if>
                              <%-- Ended by Tulasi--%>
                            </c:if>
                      </c:if>
                    </c:forEach>
                  </ol>
                </display:column>
                <%--<display:column title="No Pengenalan">
                    <ol>
                    <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai" varStatus="a">                                            
                        <c:if test="${(senarai.jenis.kod eq 'PM' or senarai.jenis.kod eq 'PA' or senarai.jenis.kod eq 'WAR' or
                                      senarai.jenis.kod eq 'ASL' or senarai.jenis.kod eq 'JA' or senarai.jenis.kod eq 'JK' or
                                      senarai.jenis.kod eq 'KL' or senarai.jenis.kod eq 'PDP' or senarai.jenis.kod eq 'PK' or
                                      senarai.jenis.kod eq 'PLK' or senarai.jenis.kod eq 'PP' or senarai.jenis.kod eq 'RP' or
                                      senarai.jenis.kod eq 'WKL' or senarai.jenis.kod eq 'WPA' or senarai.jenis.kod eq 'WS')
                                      and senarai.aktif eq 'Y'}">
                            added by Tulasi
                            <c:if test="${actionBean.noPengenalan eq null && actionBean.namaPemilik eq null}">
                                <li>
                                    <c:out value="${senarai.pihak.noPengenalan}" />
                                </li>
                            </c:if>
                            <c:if test="${actionBean.noPengenalan ne null || actionBean.namaPemilik ne null}">
                                <c:if test="${actionBean.namaPemilik eq senarai.pihak.nama || actionBean.noPengenalan eq senarai.pihak.noPengenalan}">
                                    <li>
                                        <c:out value="${senarai.pihak.noPengenalan}" />
                                    </li>
                                </c:if>
                            </c:if>
                             Ended by Tulasi
                        </c:if>                                            
                    </c:forEach>
                    </ol>
                </display:column>--%>
                <%--     <display:column title="Jenis Pengenalan" >
                         <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai" >
                             <c:out value="${senarai.pihak.jenisPengenalan.nama}" /><br>
                         </c:forEach>
                     </display:column>--%>
                <%--<display:column property="hakmilik.daerah.nama" title="Daerah" class="${line_rowNum}"/>--%>

                <%--<display:column property="hakmilik.lot.nama" title="Kod Lot" />--%>
                <display:column title="No Lot/PT">
                  <div align="center">${line.hakmilik.noLot}</div>
                </display:column>
                <display:column title="Jenis">
                  <div align="center">${line.hakmilik.kodHakmilik.kod}</div>
                </display:column>
                <display:column property="baki" title="Jumlah Perlu Bayar (RM)" class="${line_rowNum}" format="{0,number, 0.00}" style="width:100;text-align:right;"/>
                <%--   <display:column title="Status Pembayaran"  style="text-align:right">
                       ${line.baki <= 0 ? "BAYAR" : "BELUM BAYAR" }
                   </display:column>--%>
                <display:column property="hakmilik.kodStatusHakmilik.nama" title="Status Hakmilik"  style="width:100;text-align:right"/>
                <display:column property="status.nama" title="Status Akaun"  style="width:100;text-align:right"/>
                <display:column title="Papar"><s:button name="papar" value="Papar" class="btn" onclick="doSubmit('papar',this.form,'${line.hakmilik.idHakmilik}','${line.noAkaun}')"/></display:column>
              </display:table>
            </div>

          </fieldset>
        </div>
      </c:if>
    </s:form>
  </body>
</html>