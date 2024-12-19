<%-- 
    Document   : rekodTuntutanLebihan
    Created on : 18-Oct-2010, 11:42:00
    Author     : nordiyana
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    function removeSingle(id)
    {
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/rekodTuntut?deleteSingle&id='
                +id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    <%-- alert(ialertd);--%>
        }
        function tambahBaru(){
            window.open("${pageContext.request.contextPath}/pengambilan/rekodTuntut?hakMilikPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function refreshPageHakmilik(){
            var url = '${pageContext.request.contextPath}/pengambilan/rekodTuntut?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        $(document).ready( function() {


            var len = $(".daerah").length;

            for (var i=0; i<=len; i++){
                $('.hakmilik'+i).click( function() {
                    window.open("${pageContext.request.contextPath}/pengambilan/rekodTuntut?popup&idHakmilik="+$(this).text(), "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
                });
            }
        });

        function validateLuas(idVar,rowNo){

            var str = idVar.value;
            var luasTerlibat = parseInt(idVar.value);
            var luas = parseInt(document.getElementById("luas"+rowNo).value);

            if(luasTerlibat > luas){
                alert("Luas Diambil must less than Luas");
                idVar.value = str.substring(0,str.length-1);
                idVar.focus();
                return true;
            }
        }

        function validateKodUnitLuas(idVar,rowNo){
    <%--alert(idVar.value);--%>
            if(idVar.value == 'M'){
                var unitLuasDiambil = "Meter Persegi";
                alert(unitLuasDiambil);
            }
            if(idVar.value == 'H'){
                var unitLuasDiambil = "Hektar";
                alert(unitLuasDiambil);
            }

            var unitLuas = document.getElementById("unitLuas"+rowNo).value;
            alert(unitLuas);

        }

        function save(event, f){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);

            },'html');

        }

        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }

        function removeNonNumeric( strString )
        {
            var strValidCharacters = "1234567890";
            var strReturn = "";
            var strBuffer = "";
            var intIndex = 0;
            // Loop through the string
            for( intIndex = 0; intIndex < strString.length; intIndex++ )
            {
                strBuffer = strString.substr( intIndex, 1 );
                // Is this a number
                if( strValidCharacters.indexOf( strBuffer ) > -1 )
                {
                    strReturn += strBuffer;
                }
            }
            return strReturn;
        }
        function ajaxLink(link, update) {
    <%--  alert(update);
      alert(link);
      $.get(link, function(data) {
          $(update).html(data);
          $(update).show();
      });
      return false;
  }--%>
          $.get(link, function(data) {
              $(update).html(data);
              $(update).show();
          });
          return false;
      }
      
      function semak(f,s)
      {
            
          var bil=0;var bila=0;var bilb=0;
          for (i = 0; i < f; i++){
              var c = document.getElementById("noLotBaru"+i);
              var lp = document.getElementById("luasPelanB1"+i);
              var nt = document.getElementById("nilaiTanah"+i);
              var d=c.value;
               if (c == null) break;
              if(d== ''){
                  bil++;
              }
               if(lp.value== ''){
                  bila++;
              }
               if(nt.value== ''){
                  bilb++;
              }
          }
          if(bil>0){
              alert("Sila Masukkan No Lot Baru")
              return false;
          }
          if(bila>0){
              alert("Sila Masukkan Luas Pelan B1")
              return false;
          }
          if(bilb>0){
              alert("Sila Masukkan Nilai Tanah")
              return false;
          }
           doSubmit(s.form, s.name, 'page_div')
      }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.rekodTuntutanLebihanActionBean">
    <s:messages/>
    <div  id="hakmilik_details">

        <div class="subtitle displaytag">

            <fieldset class="aras1">


                <c:if test="${edit}">
                    <legend>
                        Rekod Penerimaan Pampasan/Tuntutan Lebihan 
                    </legend>
                   
                        <br>
                        <b>  Sila Klik Id hakmilik selepas masukkan luas pelan B1 dan nilai tanah untuk melihat jumlah pampasan tambahan. Bagi Keluasan B1 yang sama dengan Borang K (X4) tiada pampasan tambahan.</b>
                     <p align="center">  
                         <%--    <table align="left" >
                                    <tr>
                                        <td><label for="nolot">Nilaian Tanah :</label></td>
                                        <td><s:text name="nilaiTanah2" size="20" id="noLot"/></td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>--%>
                        <br>
                        <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0"
                                       requestURI="/pengambilan/maklumat_asas_pengambilan" id="line">
                            <%--  <div id="hakmiliks${line_rowNum}">--%>
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Hakmilik">
                                <s:link beanclass="etanah.view.stripes.pengambilan.rekodTuntutanLebihanActionBean"
                                        event="pihakDetails" onclick="return ajaxLink(this,'#hakmilik_details');" >
                                    <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                    ${line.hakmilik.idHakmilik}
                                </s:link>
                            </display:column>
                            <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                            <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                            <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                            <display:column title="Luas Sebenar">
                                <s:hidden name="luas1" value="${line.hakmilik.luas}" id="luas${line_rowNum-1}" />
                                <s:hidden name="unitLuas" value="${line.hakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                                <fmt:formatNumber pattern="###0.0000" value="${line.hakmilik.luas}"/>
                                &nbsp;${line.hakmilik.kodUnitLuas.nama}
                            </display:column>
                            <display:column title="Luas Diambil">
                                <s:text name="luasTerlibat[${line_rowNum - 1}]" readonly="true" id="luasTerlibat${line_rowNum - 1}"  onkeyup="validateLuas(this,${line_rowNum - 1})"/>
                                &nbsp;${line.hakmilik.kodUnitLuas.nama}
                            </display:column>
                            <display:column title="Baki Luas" >
                                <c:set value="${line.luasTerlibat}" var="a"/>
                                <c:set value="${line.hakmilik.luas}" var="c"/>
                                <fmt:formatNumber pattern="###0.0000" value="${c-a}"/>
                                &nbsp;${line.hakmilik.kodUnitLuas.nama}
                            </display:column>
                            <display:column title="Ukur Halus" >
                                <c:set value="${line.luasTerlibat}" var="a"/>
                                <c:set value="${line.luasPelanB1}" var="b"/>
                                <c:set value="${line.hakmilik.luas}" var="c"/>
                                <fmt:formatNumber pattern="###0.0000" value="${(c-a)-b}"/>
                                &nbsp;${line.hakmilik.kodUnitLuas.nama}
                            </display:column>
                            <display:column title="Luas Pelan B1">
                                <s:text name="luasPelanB1[${line_rowNum - 1}]" id="luasPelanB1${line_rowNum - 1}"/>
                                &nbsp;${line.hakmilik.kodUnitLuas.nama}
                            </display:column>
                            <display:column title="No Lot Baru">
                                <s:text size="8" name="noLotBaru[${line_rowNum - 1}]" id="noLotBaru${line_rowNum - 1}"/>

                                <%--<s:text name="nilaiTanah[${line_rowNum - 1}]" readonly="true" id="nilaiTanah${line_rowNum - 1}"/>--%>
                            </display:column>
                            <display:column title="Keluasan Tanah" >
                                &nbsp;${line.nomborRujukan}
                            </display:column>
                            <display:column title="Nilai Tanah (RM)">
                                <c:set value="${line_rowNum}" var="f"/>
                                <s:text name="nilaiTanah[${line_rowNum - 1}]" id="nilaiTanah${line_rowNum - 1}"/>
                                &nbsp;${line.hakmilik.kodUnitLuas.nama}

                            </display:column>
                            </div>
                        </display:table>
                        <br>
                        <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="semak(${f},this)"/>

                    </c:if>
                    <c:if test="${!edit}">
                    <fieldset class="aras1">
                        <legend>
                            Maklumat Tanah
                        </legend>
                        <p align="center">
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0"
                                           requestURI="/pengambilan/maklumat_asas_pengambilan" id="line">
                                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                <display:column title="Hakmilik">
                                    <%--${line.hakmilik.idHakmilik}--%>
                                    <s:link beanclass="etanah.view.stripes.pengambilan.rekodTuntutanLebihanActionBean"
                                            event="pihakDetails2" onclick="return ajaxLink(this,'#hakmilik_details');" >
                                        <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                        ${line.hakmilik.idHakmilik}
                                    </s:link>
                                </display:column>
                                <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                                <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                                <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                                <display:column title="Luas Sebenar">
                                    <s:hidden name="luas1" value="${line.hakmilik.luas}" id="luas${line_rowNum-1}" />
                                    <s:hidden name="unitLuas" value="${line.hakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                                    <fmt:formatNumber pattern="###0.0000" value="${line.hakmilik.luas}"/>
                                    &nbsp;${line.hakmilik.kodUnitLuas.nama}
                                </display:column>
                                <display:column title="Luas Diambil"><fmt:formatNumber  pattern="###0.0000" value="${line.luasTerlibat}" />
                                    <%--<s:text name="luasTerlibat[${line_rowNum - 1}]" readonly="true" id="luasTerlibat${line_rowNum - 1}"  onkeyup="validateLuas(this,${line_rowNum - 1})"/>--%>
                                    &nbsp;${line.hakmilik.kodUnitLuas.nama}
                                </display:column>
                                <display:column title="Baki Luas">
                                    <c:set value="${line.luasTerlibat}" var="a"/>
                                    <c:set value="${line.hakmilik.luas}" var="c"/>
                                    <fmt:formatNumber pattern="###0.0000" value="${c-a}"/>
                                    &nbsp;${line.hakmilik.kodUnitLuas.nama}
                                </display:column>
                                <display:column title="Ukur Halus (Diambil)" >
                                    <c:set value="${line.luasTerlibat}" var="a"/>
                                    <c:set value="${line.luasPelanB1}" var="b"/>
                                    <c:set value="${line.hakmilik.luas}" var="c"/>
                                    <fmt:formatNumber pattern="###0.0000" value="${(c-a)-b}"/>
                                    &nbsp;${line.hakmilik.kodUnitLuas.nama}
                                </display:column>
                                <display:column title="Luas Pelan B1">
                                    <fmt:formatNumber pattern="###0.0000" value="${line.luasPelanB1}"/>
                                    <%--<s:text name="luasPelanB1[${line_rowNum - 1}]" readonly="true" id="luasPelanB1${line_rowNum - 1}"/>--%>
                                    &nbsp;${line.hakmilik.kodUnitLuas.nama}
                                </display:column>

                                <display:column title="Keluasan Tanah" >

                                    &nbsp;${line.nomborRujukan}
                                </display:column>
                                <display:column title="No Lot Baru">
                                    <fmt:formatNumber pattern="###0" value="${line.lokaliti}" var="lb"/>
${line.lokaliti}
                                    <%--<s:text name="nilaiTanah[${line_rowNum - 1}]" readonly="true" id="nilaiTanah${line_rowNum - 1}"/>--%>
                                </display:column>
                                <display:column title="Nilai Tanah (RM)">
                                    <fmt:formatNumber pattern="##,###,###,##0.00" value="${line.nilaianJpph}" var="z"/>
                                    ${z}&nbsp; <%-- ${line.hakmilik.kodUnitLuas.nama}--%>
                                    <%--<s:text name="nilaiTanah[${line_rowNum - 1}]" readonly="true" id="nilaiTanah${line_rowNum - 1}"/>--%>
                                </display:column>

                            </display:table>
                    </fieldset>

                </c:if>
            </fieldset>
            K4 : Keluasan Tanah tidak lebih dari 1/4 Hektar<BR>
            L4 : Keluasan Tanah lebih dari 1/4 Hektar<BR>
            X4 : Sama dengan Borang K<BR>
            <c:if test="${showDetails}">
                <fieldset class="aras1">
                    <legend>Pampasan Tambahan - ID Hakmilik : ${actionBean.hakmilik.idHakmilik}</legend><br />
                    <div align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiPermohonanPihak}" pagesize="20" cellpadding="0" cellspacing="0"
                                       requestURI="/pengambilan/maklumat_asas_pengambilan" id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column property="pihak.nama" title="Pemilik Berdaftar" />
                            <display:column property="pihak.noPengenalan" title="No KP" />
                            <display:column title="Bahagian" >

                                ${line.syerPembilang}/${line.syerPenyebut}
                            </display:column>

                            <c:if test="${actionBean.kodKedesakan eq 'DE'}">

                                <display:column  title="Tempoh Borang K" >
                                    Dari :
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.tarikhTerimaK}"/><br/>
                                    Terkini : 
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.calendar}"/><br/><br/>
                                    Jurang Tempoh :                             
                                    <s:text  id="tempohK" name="tempohK" value="${actionBean.tempohK} " />
                                    <br/>
                                    <c:if test="${actionBean.tempohK eq null}">
                                        <fieldset>
                                            Jurang tempoh tidak dipaparkan jika tempoh kurang daripada sebulan
                                        </fieldset>
                                    </c:if>

                                </display:column>

                                <display:column title="Nilaian Pampasan (RM)" >
                                    <%--  <c:set value="${line.jumlahPenyebut}" var="z"/>--%>
                                    <c:forEach items="${actionBean.hakmilikPermohonanList}" var="list" >
                                        <c:if test="${list.hakmilik.idHakmilik eq actionBean.hakmilik.idHakmilik}">
                                            <c:set value="${actionBean.tempohHyear}" var="y"/>
                                            <c:set value="${actionBean.tempohHmonth}" var="m"/>
                                            <%--     <c:set value="${y}" var="a"/>
                                                 <c:set value="${m}" var="b"/>--%>
                                            <%--     <fmt:formatNumber pattern="###,###,###0.0000000000"  value="${a+b}" var="c" />--%>
                                            <c:set value="${list.luasUkurHalus}" var="i"/>
                                            <c:set value="${list.nilaianJpph}" var="j"/>
                                            <c:set value="${j*i}" var="pampas"/>
                                            <c:set value="${pampas*0.08}" var="jumpampas"/>
                                            <c:set value="${jumpampas/12*m}" var="kadarsebulan"/>
                                            <c:set value="${jumpampas*y}" var="kadarsetahun"/>
                                            <c:set value="${kadarsetahun+kadarsebulan}" var="jumlahfaedah"/>
                                            <c:set value="${pampas+jumlahfaedah}" var="jumlahbesar"/>


                                            <c:set value="  ${line.syerPembilang}" var="k"/>
                                            <c:set value=" ${line.syerPenyebut}" var="l"/>
                                            <%-- <c:set value=" ${actionBean.faedah}" var="z"/>--%>
                                            <c:if test="${i<0.0}">
                                                <s:text id="pampasan${line_rowNum-1}" name="pampasan${line_rowNum-1}" value="${jumlahbesar*(k/l)*-1}" disabled="true"/>
                                            </c:if>
                                            <c:if test="${i>0.0}">
                                                <s:text id="pampasan${line_rowNum-1}" name="pampasan${line_rowNum-1}" value="${jumlahbesar*(k/l)}" disabled="true"/>
                                            </c:if>
                                        </c:if>

                                    </c:forEach>
                                </display:column>
                            </c:if>
                            ${actionBean.kodKedesakan}
                            <c:if test="${actionBean.kodKedesakan eq 'XK'}">

                                <display:column  title="Tempoh Borang H" >
                                    Dari :
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.tarikhTerimaH}"/><br/>
                                    Terkini : 
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.calendar}"/><br/><br/>
                                    Jurang Tempoh :                             
                                    <s:text  id="tempohH" name="tempohH" value="${actionBean.tempohH} " />
                                    <br/>
                                    <c:if test="${actionBean.tempohH eq null}">
                                        <fieldset style="font-size:14; color: red">
                                            *Jurang tempoh tidak dipaparkan jika tempoh kurang daripada sebulan
                                        </fieldset>
                                    </c:if>

                                </display:column>  


                                <display:column title="Nilaian Pampasan (RM)" >
                                    <%--  <c:set value="${line.jumlahPenyebut}" var="z"/>--%>
                                    <c:forEach items="${actionBean.hakmilikPermohonanList}" var="list" >
                                        <c:if test="${list.hakmilik.idHakmilik eq actionBean.hakmilik.idHakmilik}">
                                            <c:set value="${actionBean.tempohHyear}" var="y"/>
                                            <c:set value="${actionBean.tempohHmonth}" var="m"/>
                                            <%--  <c:set value="${y}" var="a"/>
                                              <c:set value="${m}" var="b"/>
                                              <fmt:formatNumber pattern="###,###,###0.0000000000"  value="${a+b}" var="c" />--%>
                                            <c:set value="${list.luasUkurHalus}" var="i"/>
                                            <c:set value="${list.nilaianJpph}" var="j"/>
                                            <fmt:formatNumber pattern="#########0.0000000000" value="${j*i}" var="pampas"/>
                                            <fmt:formatNumber pattern="#########0.0000000000" value="${pampas*0.08}" var="jumpampas"/>
                                            <fmt:formatNumber pattern="#########0.0000000000" value="${jumpampas/12*m}" var="kadarsebulan"/>
                                            <fmt:formatNumber pattern="#########0.0000000000" value="${jumpampas*y}" var="kadarsetahun"/>
                                            <c:set value="${kadarsetahun+kadarsebulan}" var="jumlahfaedah"/>
                                            <c:set value="${pampas+jumlahfaedah}" var="jumlahbesar"/>

                                            <c:set value="  ${line.syerPembilang}" var="k"/>
                                            <c:set value=" ${line.syerPenyebut}" var="l"/>
                                            <%-- <c:set value=" ${actionBean.faedah}" var="z"/>--%>
                                            <c:if test="${i<0.0}">
                                                <s:text id="pampasan${line_rowNum-1}" name="pampasan${line_rowNum-1}" value="${jumlahbesar*(k/l)*-1}" disabled="true"/>
                                            </c:if>
                                            <c:if test="${i>0.0}">
                                                <s:text id="pampasan${line_rowNum-1}" name="pampasan${line_rowNum-1}" value="${jumlahbesar*(k/l)}" disabled="true"/>
                                            </c:if>
                                        </c:if>

                                    </c:forEach>
                                </display:column>
                            </c:if>
                        </display:table>
                        <br>
                    </div>
                    <br /><br />
                </fieldset><br />
            </c:if>
        </div>
    </div>

</s:form>