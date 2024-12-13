<%--
    Document   : pembetul_maklumatHakmilikAsal
    Created on : Dec 23, 2009, 3:19:12 PM
    Author     : mohd.fairul
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<style type="text/css">
    td.s{
        font-weight:bold;
        color:blue;
        text-align: right;
    }

    .arrow { background:transparent url(${pageContext.request.contextPath}/pub/images/arrows.png) no-repeat scroll 0px -16px; width:100%; height:16px; display:block;}
    .up { background-position:0px 0px;}
</style>
<script language="javascript">

     $(document).ready( function(){

        $('#PM').toggle();
        $('#PG').toggle();
        $('#KVT').toggle();
        $('#PJ').toggle();
        $('#PT').toggle();
        $('#PI').toggle();
        $('#WAR').toggle();
        $('#TPB').toggle();
        $('#TPBT').toggle();
        $('#TWAR').toggle();


    <%--set focus--%>
                $('input').focus(function() {
                    $(this).addClass("focus");
                });

                $('input').blur(function() {
                    $(this).removeClass("focus");
                });

                $('select').focus(function() {
                    $(this).addClass("focus");
                });

                $('select').blur(function() {
                    $(this).removeClass("focus");
                });
            });


    function editPop(idH,nama)
    {
    <%--alert("Success IDH::"+ idH+" "+"Success IDHA::"+idHA);--%>
    <%--alert(nama)--%>
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?editPemilik';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=800,height=250,scrollbars=yes");
    }

    function popup(url)
    {
        params  = 'width='+screen.width;
        params += ', height='+screen.height;
        params += ', top=0, left=0'
        params += ', fullscreen=yes';
        params += ', directories=no';
        params += ', location=no';
        params += ', menubar=no';
        params += ', resizable=no';
        params += ', scrollbars=yes';
        params += ', status=no';
        params += ', toolbar=no';
        newwin=window.open(url,'PopUp', params);
        if (window.focus) {newwin.focus()}
        return false;
    }

    function showMe(thID){

                $('#'+thID).toggle();
                $('.'+thID).find(".arrow").toggleClass("up");
            }
</script>


<s:form beanclass="etanah.view.stripes.nota.pembetulanActionBean">

    <s:messages />
    <s:errors />
   
         <fieldset class="aras1">
              <legend>
                  Baiki / Mansuh Pihak Berkepentingan
            </legend>
             <br/>
              <div class="subtitle" align="center">
        <table class="tablecloth" width="70%" style="margin-left: auto; margin-right: auto;">
            <tr onclick="showMe('PM')" onmouseover="this.style.cursor='pointer'; this.style.text" class="PM"><th><span class="arrow">Senarai Pemilik</span></th></tr>
            <tr id="PM">
                <td>
                    <fieldset class="aras1">
            <legend>
            </legend>
            <p style="color:red">
                *Klik butang (<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif'/>) untuk membuat pembetulan.<br/>
            </p>
            <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.hpBerkepentinganPemilikList}"  cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/common/pihak" pagesize="5">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column property="pihak.nama" title="Nama" />
                    <display:column property="pihak.noPengenalan" title="No Pengenalan"/>
                    <display:column property="jenis.nama" title="Jenis Pihak"/>
                    <display:column title="Baiki">
                        <div align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?editPemilik&idPihak=${line.pihak.idPihak}&kodPB=${line.jenis.kod}&idH=${line.hakmilik.idHakmilik}')"/>
                        </div>
                    </display:column>
                    <display:column title="Mansuh">
                        <div align="center">
                            <img alt='Klik Untuk Mansuh' border='0' title="Klik Untuk Mansuh" src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?popupSebelumkini&idH=${listHS.hakmilik.idHakmilik}&idHS=${listHS.hakmilikSebelum}')"/>

                        </div>
                    </display:column>
                </display:table>
            </div>
        </fieldset>
                </td>
            </tr>
        <%--end of senarai pemilik--%>
         <tr onclick="showMe('PG')" onmouseover="this.style.cursor='pointer'; this.style.text" class="PG"><th><span class="arrow">Senarai Pemegang Gadai</span></th></tr>
         <tr id="PG">
                <td>
                    <fieldset class="aras1">
            <legend>
                
            </legend>
            <p style="color:red">
                *Klik butang (<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif'/>) untuk membuat pembetulan.<br/>
            </p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hpBerkepentinganPGList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/common/pihak" id="line">

                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column property="pihak.nama" title="Nama" />
                    <display:column property="pihak.noPengenalan" title="No Pengenalan"/>
                    <display:column property="jenis.nama" title="Jenis Pihak"/>
                    <display:column title="Baiki">

                        <div align="center">
                               <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?editPemilik&idPihak=${line.pihak.idPihak}')"/>
                        </div>
                    </display:column>
                    <display:column title="Mansuh">
                        <div align="center">
                            <img alt='Klik Untuk Mansuh' border='0' title="Klik Untuk Mansuh" src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?popupSebelumkini&idH=${listHS.hakmilik.idHakmilik}&idHS=${listHS.hakmilikSebelum}')"/>

                        </div>
                    </display:column>
                </display:table>
            </div>
  </fieldset>
                </td>
            </tr>
            <%--end of pemegang gadaian--%>
            <tr onclick="showMe('KVT')" onmouseover="this.style.cursor='pointer'; this.style.text" class="KVT"><th><span class="arrow">Senarai Pengkaveatan</span></th></tr>
            <tr id="KVT">
                <td>
                     <fieldset class="aras1">
            <legend>
                 </legend>
            <p style="color:red">
                *Klik butang (<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif'/>) untuk membuat pembetulan.<br/>
            </p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hpBerkepentinganKAVEATList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/common/pihak" id="line">

                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column property="pihak.nama" title="Nama" />
                    <display:column property="pihak.noPengenalan" title="No Pengenalan"/>
                    <display:column property="jenis.nama" title="Jenis Pihak"/>
                    <display:column title="Baiki">

                        <div align="center">
                                   <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?editPemilik&idPihak=${line.pihak.idPihak}')"/>
                        </div>
                    </display:column>
                    <display:column title="Mansuh">
                        <div align="center">
                            <img alt='Klik Untuk Mansuh' border='0' title="Klik Untuk Mansuh" src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?popupSebelumkini&idH=${listHS.hakmilik.idHakmilik}&idHS=${listHS.hakmilikSebelum}')"/>

                        </div>
                    </display:column>
                </display:table>
            </div>

        </fieldset>
                </td>
            </tr>
<%--end of pengkeveatan--%>
  <tr onclick="showMe('PJ')" onmouseover="this.style.cursor='pointer'; this.style.text" class="PJ"><th><span class="arrow"> Senarai Pemegang Pajakan</span></th></tr>
  <tr id="PJ">
                <td>
                        <fieldset class="aras1">
            <legend>

            </legend>
            <p style="color:red">
                *Klik butang (<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif'/>) untuk membuat pembetulan.<br/>
            </p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hpBerkepentinganPJList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/common/pihak" id="line">

                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column property="pihak.nama" title="Nama" />
                    <display:column property="pihak.noPengenalan" title="No Pengenalan"/>
                    <display:column property="jenis.nama" title="Jenis Pihak"/>
                    <display:column title="Baiki">

                        <div align="center">
                               <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?editPemilik&idPihak=${line.pihak.idPihak}')"/>
                        </div>
                    </display:column>
                    <display:column title="Mansuh">
                        <div align="center">
                            <img alt='Klik Untuk Mansuh' border='0' title="Klik Untuk Mansuh" src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?popupSebelumkini&idH=${listHS.hakmilik.idHakmilik}&idHS=${listHS.hakmilikSebelum}')"/>

                        </div>
                    </display:column>
                </display:table>
            </div>

        </fieldset>
                </td>
            </tr>
<%--end of pemegang pajakan--%>
 <tr onclick="showMe('PT')" onmouseover="this.style.cursor='pointer'; this.style.text" class="PT"><th><span class="arrow"> Senarai Penerima Tenansi</span></th></tr>
 <tr id="PT">
                <td>
                         <fieldset class="aras1">
            <legend>
             
            </legend>
            <p style="color:red">
                *Klik butang (<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif'/>) untuk membuat pembetulan.<br/>
            </p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hpBerkepentinganTENList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/common/pihak" id="line">

                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column property="pihak.nama" title="Nama" />
                    <display:column property="pihak.noPengenalan" title="No Pengenalan"/>
                    <display:column property="jenis.nama" title="Jenis Pihak"/>
                    <display:column title="Baiki">

                        <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?editPemilik&idPihak=${line.pihak.idPihak}')"/>
                        </div>
                    </display:column>
                    <display:column title="Mansuh">
                        <div align="center">
                            <img alt='Klik Untuk Mansuh' border='0' title="Klik Untuk Mansuh" src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?popupSebelumkini&idH=${listHS.hakmilik.idHakmilik}&idHS=${listHS.hakmilikSebelum}')"/>

                        </div>
                    </display:column>
                </display:table>
            </div>

        </fieldset>
                </td>
            </tr>
            <%--end of senarai tenasi--%>
             <tr onclick="showMe('PI')" onmouseover="this.style.cursor='pointer'; this.style.text" class="PI"><th><span class="arrow">Senarai Pemasuk Ismen</span></th></tr>
             <tr id="PI">
                <td>
                       <fieldset class="aras1">
            <legend>
                
            </legend>
            <p style="color:red">
                *Klik butang (<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif'/>) untuk membuat pembetulan.<br/>
            </p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hpBerkepentinganPIList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/common/pihak" id="line">

                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column property="pihak.nama" title="Nama" />
                    <display:column property="pihak.noPengenalan" title="No Pengenalan"/>
                    <display:column property="jenis.nama" title="Jenis Pihak"/>
                    <display:column title="Baiki">

                        <div align="center">
                               <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?editPemilik&idPihak=${line.pihak.idPihak}&kodPB=${line.jenis.nama}')"/>
                        </div>
                    </display:column>
                    <display:column title="Mansuh">
                        <div align="center">
                            <img alt='Klik Untuk Mansuh' border='0' title="Klik Untuk Mansuh" src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?popupSebelumkini&idH=${listHS.hakmilik.idHakmilik}&idHS=${listHS.hakmilikSebelum}')"/>

                        </div>
                    </display:column>
                </display:table>
            </div>

        </fieldset>
                </td>
            </tr>
            <%--end of ismen--%>
            <tr onclick="showMe('WAR')" onmouseover="this.style.cursor='pointer'; this.style.text" class="WAR"><th><span class="arrow">Senarai Waris</span></th></tr>
            <tr id="WAR">
                <td>
                      <fieldset class="aras1">
            <legend>
                
            </legend>
            <p style="color:red">
                *Klik butang (<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif'/>) untuk membuat pembetulan.<br/>
            </p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hpBerkepentinganWarisList}" pagesize="10" cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/common/pihak" id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column property="pihak.nama" title="Nama" />
                    <display:column property="pihak.noPengenalan" title="No Pengenalan"/>
                    <display:column property="jenis.nama" title="Jenis Pihak"/>
                    <display:column title="Baiki">
                        <div align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editPop('${line.hakmilik.idHakmilik}', '${line.pihak.nama}')"/>
                        </div>
                    </display:column>
                    <display:column title="Mansuh">
                        <div align="center">
                            <img alt='Klik Untuk Mansuh' border='0' title="Klik Untuk Mansuh" src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?popupSebelumkini&idH=${listHS.hakmilik.idHakmilik}&idHS=${listHS.hakmilikSebelum}')"/>

                        </div>
                    </display:column>
                </display:table>
            </div>
      </fieldset>
                </td>
                       </tr>
                       <%--end of waris--%>
        </table>
        <br/>
    </div>
         </fieldset>
<%--End Senarai PB edit/mansuh--%>
<%--Start Tambah PB--%>
    
   
<br/>
<fieldset class="aras1"><legend>Tambah</legend>
     <div class="subtitle" align="center">
    <br/>
        <table class="tablecloth" width="70%" style="margin-left: auto; margin-right: auto;">
            <tr onclick="showMe('TPB')" onmouseover="this.style.cursor='pointer'; this.style.text" class="TPB"><th><span class="arrow">Tambah Pihak Berkepentingan</span></th></tr>
            <tr id="TPB">
                <td>
                     <fieldset class="aras1">
            <legend>
                
            </legend>
            <p style="color:red">
                *Klik butang (<img alt='Klik Untuk Tambah' border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png'/>) untuk menambah Pihak Berkepentingan.<br/>
            </p>
            <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/common/pihak" pagesize="5">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column title="Tambah PB">
                        <div align="center">
                            <img alt='Klik Untuk Kemaskini' title="Klik Untuk Tambah" border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?tambahPihakBerkepentingan&idH=${line.hakmilik.idHakmilik}')"/>
                        </div>
                    </display:column>
                </display:table>
            </div>
    </fieldset>
                </td>
            </tr>
            <%--end of tambah pihak berkepentingan--%>
             <tr onclick="showMe('TPBT')" onmouseover="this.style.cursor='pointer'; this.style.text" class="TPBT"><th><span class="arrow">Tambah Pihak Berkepentingan Terlibat</span></th></tr>
             <tr id="TPBT">
                <td>
                       <fieldset class="aras1">
            <legend>
                
            </legend>
           <p style="color:red">
                *Klik butang (<img alt='Klik Untuk Tambah' border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png'/>) untuk menambah Pihak Berkepentingan Terlibat.<br/>
            </p>
            <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/common/pihak" pagesize="5">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column title="Tambah PB Terlibat">
                        <div align="center">
                            <img alt='Klik Untuk Kemaskini' title="Klik Untuk Tambah" border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?tambahPihakBerkepentinganTerlibat&idH=${line.hakmilik.idHakmilik}')"/>
                        </div>
                    </display:column>
                </display:table>
            </div>

        </fieldset>
                </td>
            </tr>
            <%--end of tambah pihak berkenpentingan terlibat--%>
             <tr onclick="showMe('TWAR')" onmouseover="this.style.cursor='pointer'; this.style.text" class="TWAR"><th><span class="arrow">Tambah Waris</span></th></tr>
             <tr id="TWAR">
                <td>
                      <fieldset class="aras1">
            <legend>

            </legend>
            <p style="color:red">
                *Klik butang (<img alt='Klik Untuk Tambah' border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png'/>) untuk menambah Waris.<br/>
            </p>
            <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/common/pihak" pagesize="5">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column title="Tambah Waris">
                        <div align="center">
                            <img alt='Klik Untuk Kemaskini' title="Klik Untuk Tambah" border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?tambahPihakBerkepentinganWaris&idH=${line.hakmilik.idHakmilik}')"/>
                        </div>
                    </display:column>
                </display:table>
            </div>

        </fieldset>
                </td>
            </tr>

        </table>

      
<br/></div>
        </fieldset>
     
      
 <br/>
    
   
   

</s:form>
